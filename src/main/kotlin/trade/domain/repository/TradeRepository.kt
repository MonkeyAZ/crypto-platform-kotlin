package org.example.trade.domain.repository

import org.example.trade.domain.model.Trade
import org.example.trade.domain.model.TradeId

interface TradeRepository {

    suspend fun save(trade: Trade)

    suspend fun exists(id: TradeId) : Boolean

    suspend fun findById(id: TradeId): Trade?
}