package io.github.neronguyenvn.paymentsample.core.sdk

import io.github.neronguyenvn.paymentsample.core.sdk.model.CardToken

interface PaymentSdk {
    suspend fun readCard(): CardToken
}
