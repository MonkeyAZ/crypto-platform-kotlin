package org.example.trade.testsupport

import org.example.trade.application.ReceiveTradeCommand
import org.example.trade.infrastructure.market.MarketTradeMessage
import java.time.Instant

class TradeFixtures {
    companion object {
        fun sampleTrade(): ReceiveTradeCommand {
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