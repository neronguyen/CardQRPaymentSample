package io.github.neronguyenvn.paymentsample.core.database

import io.github.neronguyenvn.paymentsample.core.database.model.PaymentIntentEntity

interface PaymentIntentDao {

    suspend fun upsert(entity: PaymentIntentEntity)

    suspend fun get(id: String): PaymentIntentEntity?
}
