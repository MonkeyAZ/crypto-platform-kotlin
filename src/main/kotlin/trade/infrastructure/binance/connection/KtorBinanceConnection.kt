package org.example.trade.infrastructure.binance.connection

import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class KtorBinanceConnection(
    private val client: HttpClient,
    private val stream: String = "btcusdt@trade"
) : BinanceConnection {
    override fun connect(): Flow<String> = flow {
        client.webSocket(urlString = "wss://stream.binance.com:9443/ws/$stream") {
            for (frame in incoming) {
                if (frame is Frame.Text) {
                    emit(frame.readText())
                }
            }
        }
    }
}