package org.example

import org.example.trade.application.ReceiveTradeUseCase
import org.example.trade.infrastructure.event.InMemoryDomainEventPublisher
import org.example.trade.application.MarketTradeHandler
import org.example.trade.infrastructure.repository.memory.InMemoryTradeRepository
import org.example.trade.testsupport.FakeMarketDataSource

class CryptoPlatform {

    suspend fun start() {
        println("Starting Crypto Platform...")
        // Initialize the platform, repositories, and use cases here
        val repository = InMemoryTradeRepository()

        val publisher = InMemoryDomainEventPublisher()

        val marketDataSource = FakeMarketDataSource()

        val receiveTrade = ReceiveTradeUseCase(repository, publisher)

        val connector = MarketTradeHandler(marketDataSource, receiveTrade)

        connector.start()
    }
}