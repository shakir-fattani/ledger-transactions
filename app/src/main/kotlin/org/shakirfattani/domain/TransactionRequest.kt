package org.shakirfattani.domain

import kotlinx.serialization.Serializable

@Serializable
data class TransactionRequest(
    val transactionId: String,
    val amount: Double,
    val asset: String,
    val assetType: String,
    val type: String,
    val state: String,
    val createdAt: String
)

