package org.shakirfattani.api

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin
import org.shakirfattani.di.appModule
import org.shakirfattani.workflow.FeeWorkflow

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

    routing {
        val feeWorkflow: FeeWorkflow by inject()
        healthRoute()
        feeRoute(feeWorkflow)
    }
}