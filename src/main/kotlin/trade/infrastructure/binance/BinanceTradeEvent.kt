package org.example.trade.infrastructure.binance

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BinanceTradeEvent(
    @SerialName("t")
    val tradeId: Long,

    @SerialName("s")
    val symbol: String,

    @SerialName("p")
    val price: String,

    @SerialName("q")
    val quantity: String,

    @SerialName("E")
    val eventTime: Long
)