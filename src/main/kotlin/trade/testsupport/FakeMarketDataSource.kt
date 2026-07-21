package org.example.trade.testsupport

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.example.trade.application.MarketDataSource
import org.example.trade.infrastructure.market.MarketTradeMessage
import kotlin.time.Duration.Companion.milliseconds

class FakeMarketDataSource : MarketDataSource {
    override fun trades(): Flow<MarketTradeMessage> = flow {
        repeat(5) { index ->
            emit(TradeFixtures.sampleMarketTradeMessage(index))

            delay(1_000.milliseconds)
        }
    }
}