package org.shakirfattani.api

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.healthRoute() {
    get("/health") {
        call.respondText("success")
    }
}