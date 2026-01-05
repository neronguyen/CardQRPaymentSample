package io.github.neronguyenvn.paymentsample.core.network

import io.github.neronguyenvn.paymentsample.core.model.payment.MoneyAmount
import io.github.neronguyenvn.paymentsample.core.model.payment.PaymentMethod
import kotlin.uuid.Uuid

private typealias TransactionId = String

interface PaymentGateway {
    suspend fun charge(
        amount: MoneyAmount,
        idempotencyKey: String,
        cardToken: String,
    ): Result<TransactionId>
}
