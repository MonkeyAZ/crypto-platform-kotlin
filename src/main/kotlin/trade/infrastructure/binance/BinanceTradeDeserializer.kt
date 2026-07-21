package org.example.trade.infrastructure.binance

import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlinx.serialization.json.Json

class BinanceTradeDeserializer (
    private val json : Json
) {

    fun deserialize(value: Frame.Text): BinanceTradeEvent {
        return json.decodeFromString<BinanceTradeEvent>(value.readText())
    }
}