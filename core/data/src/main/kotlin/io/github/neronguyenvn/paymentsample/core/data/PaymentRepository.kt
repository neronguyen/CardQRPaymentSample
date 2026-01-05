package io.github.neronguyenvn.paymentsample.core.data

import io.github.neronguyenvn.paymentsample.core.model.payment.MoneyAmount
import io.github.neronguyenvn.paymentsample.core.model.payment.PaymentIntent
import io.github.neronguyenvn.paymentsample.core.model.payment.PaymentMethod
import kotlin.uuid.Uuid

interface PaymentRepository {

    suspend fun createPaymentIntent(
        amount: MoneyAmount,
        method: PaymentMethod
    ): PaymentIntent

    suspend fun startCardPayment(
        intentId: Uuid
    ): Result<Unit>
}
