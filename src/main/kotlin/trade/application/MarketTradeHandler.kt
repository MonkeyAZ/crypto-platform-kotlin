package org.example.trade.application

import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.retry
import java.math.BigDecimal

class MarketTradeHandler (
    private val marketDataSource: MarketDataSource,
    private val receiveTrade: ReceiveTrade
) {
    suspend fun start() {
        marketDataSource.trades()
            .retry()
            .onEach { println("Received trade message: $it") }
            .filter { it.quantity > BigDecimal.ZERO }
            .collect { message ->
            receiveTrade(
                ReceiveTradeCommand(
                    id = message.id,
                    symbol = message.symbol,
                    exchange = message.exchange,
                    price = message.price,
                    quantity = message.quantity,
                    timestamp = message.timestamp
                )
            )
        }
    }
}
