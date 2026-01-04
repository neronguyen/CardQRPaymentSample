package io.github.neronguyenvn.paymentsample.core.model.payment

data class PaymentIntent(
    val id: String,
    val amount: MoneyAmount,
    val method: PaymentMethod,
    val idempotencyKey: String,
    val status: PaymentStatus,
    val transactionId: String? = null,
)
