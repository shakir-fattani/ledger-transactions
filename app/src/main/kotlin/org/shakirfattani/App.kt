package org.shakirfattani

import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.shakirfattani.api.module


fun main() {
    println("Starting Application.......")

    embeddedServer(Netty, port = 8080, module = Application::module).start(wait = true)
}

