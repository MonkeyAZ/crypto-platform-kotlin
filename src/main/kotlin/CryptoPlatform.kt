package org.example

import org.example.trade.application.ReceiveTradeUseCase
import org.example.trade.infrastructure.event.InMemoryDomainEventPublisher
import org.example.trade.infrastructure.repository.InMemoryTradeRepository
import org.example.trade.testsupport.TradeFixtures

class CryptoPlatform {

    suspend fun start() {
        println("Starting Crypto Platform...")
        // Initialize the platform, repositories, and use cases here
        val repository = InMemoryTradeRepository()

        val publisher = InMemoryDomainEventPublisher()

        val receiveTrade = ReceiveTradeUseCase(repository, publisher)

        receiveTrade(TradeFixtures.sampleTrade())
    }
}