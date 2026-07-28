package org.example

import kotlinx.serialization.json.Json
import org.example.trade.application.ReceiveTradeUseCase
import org.example.trade.infrastructure.event.InMemoryDomainEventPublisher
import org.example.trade.application.MarketTradeHandler
import org.example.trade.infrastructure.binance.BinanceMarketDataSource
import org.example.trade.infrastructure.binance.BinanceTradeDeserializer
import org.example.trade.infrastructure.binance.BinanceTradeMapper
import org.example.trade.infrastructure.binance.connection.BinanceWebsocketFactory
import org.example.trade.infrastructure.binance.connection.ConnectionSupervisor
import org.example.trade.infrastructure.binance.connection.KtorBinanceConnection
import org.example.trade.infrastructure.repository.postgres.FlywayMigration
import org.example.trade.infrastructure.repository.postgres.TradeRepositoryFactory

class CryptoPlatform {

    suspend fun start() {
        println("Starting Crypto Platform...")

        println("Run migrations...")
        FlywayMigration.runMigration()

        println("Initializing Trade Repository...")
        val repository = TradeRepositoryFactory.create()

        println("Initializing Domain Event Publisher...")
        val publisher = InMemoryDomainEventPublisher()

        println("Initializing Binance Connection...")
        val binanceConnection = KtorBinanceConnection(BinanceWebsocketFactory.create())

        println("Initializing Connection Supervisor...")
        val connectionSupervisor = ConnectionSupervisor(binanceConnection)

        val json = Json { ignoreUnknownKeys = true }

        val deserializer = BinanceTradeDeserializer(json)

        val mapper = BinanceTradeMapper()

        println("Initializing Binance Market Data Source...")
        val marketDataSource = BinanceMarketDataSource(
            connectionSupervisor,
            deserializer,
            mapper
        )

        println("Initializing Receive Trade Use Case...")
        val receiveTrade = ReceiveTradeUseCase(repository, publisher)

        println("Initializing Market Trade Handler...")
        val connector = MarketTradeHandler(marketDataSource, receiveTrade)

        connector.start()
    }
}