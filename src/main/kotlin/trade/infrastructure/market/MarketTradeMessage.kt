package org.example.trade.infrastructure.market

import java.math.BigDecimal
import java.time.Instant

data class MarketTradeMessage(
    val id: String,
    val symbol: String,
    val exchange: String,
    val price: BigDecimal,
    val quantity: BigDecimal,
    val timestamp: Instant
)