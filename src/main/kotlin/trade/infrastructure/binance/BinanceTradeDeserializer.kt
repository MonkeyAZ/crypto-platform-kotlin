package org.example.trade.infrastructure.binance

import kotlinx.serialization.json.Json

class BinanceTradeDeserializer (
    private val json : Json
) {

    fun deserialize(value: String): BinanceTradeEvent {
        return json.decodeFromString<BinanceTradeEvent>(value)
    }
}