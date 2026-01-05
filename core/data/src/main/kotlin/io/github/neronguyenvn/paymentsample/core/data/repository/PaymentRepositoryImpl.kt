package io.github.neronguyenvn.paymentsample.core.data.repository

import io.github.neronguyenvn.paymentsample.core.data.PaymentRepository
import io.github.neronguyenvn.paymentsample.core.data.model.asEntity
import io.github.neronguyenvn.paymentsample.core.database.PaymentIntentDao
import io.github.neronguyenvn.paymentsample.core.database.model.getMoneyAmount
import io.github.neronguyenvn.paymentsample.core.model.payment.MoneyAmount
import io.github.neronguyenvn.paymentsample.core.model.payment.PaymentIntent
import io.github.neronguyenvn.paymentsample.core.model.payment.PaymentMethod
import io.github.neronguyenvn.paymentsample.core.model.payment.PaymentStatus
import io.github.neronguyenvn.paymentsample.core.network.PaymentGateway
import io.github.neronguyenvn.paymentsample.core.sdk.PaymentSdk
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.IllegalStateException
import kotlin.uuid.Uuid

internal class PaymentRepositoryImpl(
    private val paymentIntentDao: PaymentIntentDao,
    private val paymentSdk: PaymentSdk,
    private val paymentGateway: PaymentGateway,
) : PaymentRepository {

    override suspend fun createPaymentIntent(
        amount: MoneyAmount,
        method: PaymentMethod
    ): PaymentIntent {
        val paymentIntent = PaymentIntent(
            amount = amount,
            method = method
        )
        val idempotencyKey = Uuid.generateV7().toString()
        paymentIntentDao.upsert(paymentIntent.asEntity(idempotencyKey))
        return paymentIntent
    }

    override suspend fun startCardPayment(intentId: Uuid): Result<Unit> = coroutineScope {
        val paymentIntentDeferred = async {
            paymentIntentDao.get(intentId.toString())
        }

        val cardToken = paymentSdk.readCard()
        val paymentIntent = paymentIntentDeferred.await()
            ?: return@coroutineScope Result.failure(
                IllegalStateException("Payment intent not found")
            )

        paymentGateway.charge(
            amount = paymentIntent.getMoneyAmount(),
            idempotencyKey = paymentIntent.idempotencyKey,
            cardToken = cardToken.token
        ).map { transId ->
            val completed = paymentIntent.copy(
                status = PaymentStatus.Completed,
                transactionId = transId
            )
            paymentIntentDao.upsert(completed)
        }
    }
}
