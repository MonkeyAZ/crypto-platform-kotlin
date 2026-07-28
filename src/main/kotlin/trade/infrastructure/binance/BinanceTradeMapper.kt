package org.example.trade.infrastructure.binance

import org.example.trade.infrastructure.market.MarketTradeMessage
import java.time.Instant

class BinanceTradeMapper {
    fun toMarketTrade(event: BinanceTradeEvent): MarketTradeMessage {
        return  MarketTradeMessage(
            id = event.tradeId.toString(),
            symbol = event.symbol,
            price = event.price.toBigDecimal(),
            quantity = event.quantity.toBigDecimal(),
            exchange = "binance",
            timestamp = Instant.ofEpochMilli(event.eventTime)
        )
    }
}