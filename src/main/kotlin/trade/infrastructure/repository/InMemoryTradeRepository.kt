package org.example.trade.infrastructure.repository

import org.example.trade.domain.model.Trade
import org.example.trade.domain.model.TradeId
import org.example.trade.domain.repository.TradeRepository

class InMemoryTradeRepository : TradeRepository {

    private val trades = mutableMapOf<TradeId, Trade>()

    override suspend fun save(trade: Trade) {
        trades[trade.id] = trade
    }

    override suspend fun exists(id: TradeId) : Boolean{
        return trades.contains(id)
    }

    fun count(): Int {
        return trades.size
    }
}