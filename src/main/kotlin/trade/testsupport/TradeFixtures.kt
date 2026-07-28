package org.example.trade.testsupport

import org.example.trade.application.ReceiveTradeCommand
import org.example.trade.domain.model.Exchange
import org.example.trade.domain.model.Price
import org.example.trade.domain.model.Quantity
import org.example.trade.domain.model.Symbol
import org.example.trade.domain.model.Trade
import org.example.trade.domain.model.TradeId
import org.example.trade.infrastructure.market.MarketTradeMessage
import java.math.BigDecimal
import java.time.Instant

class TradeFixtures {
    companion object {
        fun sampleTrade(): Trade {
            return Trade.receive(
                id = TradeId("12345"),
                symbol = Symbol("BTCUSDT"),
                price = Price(BigDecimal("123.45")),
                quantity = Quantity(BigDecimal("0.01")),
                exchange = Exchange("Binance"),
                timestamp = Instant.now()
            )
        }

        fun sampleTradeCommand(): ReceiveTradeCommand {
            return ReceiveTradeCommand(
                id = "12345",
                symbol = "BTCUSDT",
                price = "123.45".toBigDecimal(),
                quantity = "0.01".toBigDecimal(),
                exchange = "Binance",
                timestamp = Instant.now()
            )
        }

        fun sampleMarketTradeMessage(index: Int): MarketTradeMessage {
            return MarketTradeMessage(
                id = index.toString(),
                symbol = "BTCUSDT",
                price = "123.45".toBigDecimal(),
                quantity = "0.01".toBigDecimal(),
                exchange = "Binance",
                timestamp = Instant.now()
            )
        }

    }
}