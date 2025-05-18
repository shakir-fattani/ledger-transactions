package org.shakirfattani.api

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import org.shakirfattani.domain.TransactionRequest
import org.shakirfattani.workflow.FeeWorkflow

fun Route.feeRoute(feeWorkflow: FeeWorkflow) {
    post("/transaction/fee") {
        val transaction = call.receive<TransactionRequest>()
        val response = feeWorkflow.handle(transaction)
        call.respond(HttpStatusCode.OK, response)
    }
}