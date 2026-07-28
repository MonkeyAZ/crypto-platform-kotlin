package org.example.trade.infrastructure.binance.connection

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.pingInterval
import kotlin.time.Duration.Companion.seconds

object BinanceWebsocketFactory {
    fun create(): HttpClient {
        return HttpClient(CIO) {
            install(WebSockets) {
                pingInterval = 20.seconds
            }

            engine {
                requestTimeout = 30_000
            }
        }
    }
}