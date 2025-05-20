package org.shakirfattani.di

import dev.restate.sdk.endpoint.Endpoint
import dev.restate.sdk.http.vertx.RestateHttpServer
import io.vertx.core.Vertx
import io.vertx.core.http.HttpServer
import io.vertx.core.http.HttpServerOptions

import org.koin.dsl.module
import org.shakirfattani.service.FeeService
import org.shakirfattani.service.impl.FeeServiceImpl
import org.shakirfattani.workflow.FeeWorkflow

val vertx: Vertx = Vertx.vertx()
val endpoint: Endpoint = Endpoint.builder()
    .bind(FeeServiceImpl() as FeeService)
    .build()

// Create and start the Restate HTTP server
val serverOptions: HttpServerOptions = HttpServerOptions().setPort(7070)

val restateServer: HttpServer = RestateHttpServer.fromEndpoint(vertx, endpoint, serverOptions)

val appModule = module {
    single { FeeWorkflow("http://127.0.0.1:8080") }
}