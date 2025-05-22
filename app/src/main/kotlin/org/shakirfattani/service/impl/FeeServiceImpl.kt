package org.shakirfattani.service.impl

import dev.restate.sdk.kotlin.Context
import org.shakirfattani.service.FeeService
import org.shakirfattani.service.TransactionAmountAndType
import org.shakirfattani.service.TransactionFeeAndFeeDescription

class FeeServiceImpl : FeeService {
    override suspend fun calculateFee(ctx: Context?, request: TransactionAmountAndType): TransactionFeeAndFeeDescription {
        val rate = when (request.type) {
            "Mobile Top Up" -> 0.015
            else -> 0.02
        }

        return TransactionFeeAndFeeDescription(request.amount * rate, "Standard fee rate of ${rate * 100}%")
    }
}