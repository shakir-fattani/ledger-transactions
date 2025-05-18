package org.shakirfattani.workflow

import org.shakirfattani.domain.TransactionRequest
import org.shakirfattani.domain.TransactionResponse
import org.shakirfattani.service.FeeService

class FeeWorkflow(private val feeService: FeeService) {
    fun handle(transaction: TransactionRequest): TransactionResponse {
        val (fee, description) = feeService.calculateFee(transaction.type, transaction.amount)

        return TransactionResponse(
            transactionId = transaction.transactionId,
            amount = transaction.amount,
            asset = transaction.asset,
            type = transaction.type,
            fee = fee,
            rate = "%.2f".format(fee / transaction.amount).toDouble(),
            description = description
        )
    }
}