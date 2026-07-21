package org.example.trade.infrastructure.binance

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.example.trade.application.MarketDataSource
import org.example.trade.infrastructure.binance.connection.BinanceConnection
import org.example.trade.infrastructure.market.MarketTradeMessage

class BinanceMarketDataSource (
    private val connection: BinanceConnection,
    private val deserializer: BinanceTradeDeserializer,
    private val mapper: BinanceTradeMapper,
) : MarketDataSource{

    override fun trades(): Flow<MarketTradeMessage> =
        connection
            .incomingFrames()
            .map(deserializer::deserialize)
            .map(mapper::toMarketTrade)
}