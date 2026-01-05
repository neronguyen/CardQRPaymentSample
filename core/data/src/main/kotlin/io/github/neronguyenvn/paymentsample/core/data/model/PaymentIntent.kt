package io.github.neronguyenvn.paymentsample.core.data.model

import io.github.neronguyenvn.paymentsample.core.database.model.PaymentIntentEntity
import io.github.neronguyenvn.paymentsample.core.model.payment.PaymentIntent
import kotlin.uuid.Uuid

internal fun PaymentIntent.asEntity(
    idempotencyKey: String,
    transactionId: String? = null
) = PaymentIntentEntity(
    id = id,
    amount = amount.amount,
    method = method,
    status = status,
    idempotencyKey = idempotencyKey,
    transactionId = transactionId
)
