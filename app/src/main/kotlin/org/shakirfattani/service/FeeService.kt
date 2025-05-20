package org.shakirfattani.service

import dev.restate.sdk.annotation.Handler
import dev.restate.sdk.annotation.Service
import dev.restate.sdk.kotlin.Context
import kotlinx.serialization.Serializable

@Serializable
data class TransactionAmountAndType(
    val amount: Double,
    val type: String,
)
@Serializable
data class TransactionFeeAndFeeDescription(
    val fee: Double,
    val description: String,
)

@Service
interface FeeService {

    @Handler
    suspend fun calculateFee(ctx: Context, request: TransactionAmountAndType): TransactionFeeAndFeeDescription
}