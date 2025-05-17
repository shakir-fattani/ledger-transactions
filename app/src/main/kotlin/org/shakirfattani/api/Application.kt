package org.shakirfattani.api

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin
import org.shakirfattani.di.appModule
import org.shakirfattani.domain.TransactionRequest
import org.shakirfattani.domain.TransactionResponse
import org.shakirfattani.service.FeeService

@OptIn(ExperimentalSerializationApi::class)
fun Application.module() {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
            namingStrategy = JsonNamingStrategy.SnakeCase // ✅ Converts camelCase <-> snake_case
        })
    }

    install(Koin) {
        modules(appModule)
    }

    val feeService by inject<FeeService>()

    routing {

        get("/health") {
            call.respondText("success")
        }

        post("/transaction/fee") {
            val request = call.receive<TransactionRequest>()
            val (fee, description) = feeService.calculateFee(request.type, request.amount)

            val response = TransactionResponse(
                transactionId = request.transactionId,
                amount = request.amount,
                asset = request.asset,
                type = request.type,
                fee = fee,
                rate = fee / request.amount,
                description = description
            )

            call.respond(response)
        }
    }
}