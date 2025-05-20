package org.shakirfattani

import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.shakirfattani.di.restateServer

fun main() {
    println("Starting RestateServer.......")
    restateServer.listen().andThen {
        println("Restate server started at http://localhost:7070")
    }
    println("Starting MainServer.......")
    embeddedServer(Netty, port = 9000, module = Application::module).start(wait = true)
}

