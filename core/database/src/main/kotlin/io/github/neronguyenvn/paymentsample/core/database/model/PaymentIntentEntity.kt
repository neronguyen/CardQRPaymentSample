package io.github.neronguyenvn.paymentsample.core.database.model

import io.github.neronguyenvn.paymentsample.core.model.payment.MoneyAmount
import io.github.neronguyenvn.paymentsample.core.model.payment.PaymentIntent
import io.github.neronguyenvn.paymentsample.core.model.payment.PaymentMethod
import io.github.neronguyenvn.paymentsample.core.model.payment.PaymentStatus

data class PaymentIntentEntity(
    val id: String,
    val idempotencyKey: String,
    val amount: Long,
    val method: PaymentMethod,
    val status: PaymentStatus,
    val transactionId: String? = null,
    val lastError: String? = null
)

fun PaymentIntentEntity.getMoneyAmount() = MoneyAmount(amount)
