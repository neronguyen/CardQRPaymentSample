package io.github.neronguyenvn.paymentsample.core.model.payment

import kotlin.uuid.Uuid

data class PaymentIntent(
    val id: String = Uuid.generateV7().toString(),
    val amount: MoneyAmount,
    val method: PaymentMethod,
    val status: PaymentStatus = PaymentStatus.Pending,
    val transactionId: String? = null,
)
