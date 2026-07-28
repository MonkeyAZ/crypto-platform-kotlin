package org.example.trade.infrastructure.repository.memory

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

    override suspend fun findById(id: TradeId): Trade? {
        return trades[id]
    }

    fun count(): Int {
        return trades.size
    }
}