package org.example.trade.domain.model

import org.example.trade.domain.event.TradeReceived
import java.time.Instant

class Trade private constructor(
    val id: TradeId,
    val symbol: Symbol,
    val price: Price,
    val quantity: Quantity,
    val exchange: Exchange,
    val timestamp: Instant
) : Entity() {
    override fun equals(other: Any?): Boolean =  other is Trade && this.id == other.id

    override fun hashCode(): Int = id.hashCode()

    companion object {
        fun receive(
            id: TradeId,
            symbol: Symbol,
            price: Price,
            quantity: Quantity,
            exchange: Exchange,
            timestamp: Instant
        ): Trade {
            val trade = Trade(id, symbol, price, quantity, exchange, timestamp)
            trade.raise(TradeReceived(trade))
            return trade
        }
    }
}