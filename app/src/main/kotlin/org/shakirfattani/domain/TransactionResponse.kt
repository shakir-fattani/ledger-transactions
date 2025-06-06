package org.shakirfattani.domain

import kotlinx.serialization.Serializable

@Serializable
data class TransactionResponse(
    val transactionId: String,
    val amount: Double,
    val asset: String,
    val type: String,
    val fee: Double,
    val rate: Double,
    val description: String
)
