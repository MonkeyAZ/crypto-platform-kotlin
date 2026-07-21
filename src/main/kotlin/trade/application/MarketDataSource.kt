package org.example.trade.application

import kotlinx.coroutines.flow.Flow
import org.example.trade.infrastructure.market.MarketTradeMessage

interface MarketDataSource {

    fun trades(): Flow<MarketTradeMessage>
}