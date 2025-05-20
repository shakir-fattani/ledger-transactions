package org.shakirfattani.workflow

import dev.restate.client.kotlin.attachSuspend
import org.shakirfattani.domain.TransactionRequest
import org.shakirfattani.domain.TransactionResponse
import org.shakirfattani.service.FeeServiceClient
import org.shakirfattani.service.TransactionAmountAndType

class FeeWorkflow(baseUrl: String) {
    private val client = FeeServiceClient.connect(baseUrl);
    suspend fun handle(transaction: TransactionRequest): TransactionResponse {
        try {
            val feeResponse = client
                .calculateFee(TransactionAmountAndType(transaction.amount, transaction.type))

            return TransactionResponse(
                transactionId = transaction.transactionId,
                amount = transaction.amount,
                asset = transaction.asset,
                type = transaction.type,
                fee = feeResponse.fee,
                rate = "%.2f".format(feeResponse.fee / transaction.amount).toDouble(),
                description = feeResponse.description
            )
        } catch (e: Exception) {
            e.printStackTrace()
            throw e;
        }

    }
}