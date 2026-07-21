package org.example.trade.application

import java.math.BigDecimal
import java.time.Instant

data class ReceiveTradeCommand(
    val id: String,
    val symbol: String,
    val price: BigDecimal,
    val quantity: BigDecimal,
    val exchange: String,
    val timestamp: Instant
)