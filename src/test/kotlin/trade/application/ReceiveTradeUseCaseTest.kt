package trade.application

import kotlinx.coroutines.runBlocking
import org.example.trade.application.ReceiveTradeUseCase
import org.example.trade.domain.event.TradeReceived
import org.example.trade.infrastructure.event.InMemoryDomainEventPublisher
import org.example.trade.infrastructure.repository.memory.InMemoryTradeRepository
import org.junit.jupiter.api.Test
import org.example.trade.testsupport.TradeFixtures
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ReceiveTradeUseCaseTest {

    @Test
    fun `should publish a received trade successfully`() = runBlocking {
        val repository = InMemoryTradeRepository()
        val publisher = InMemoryDomainEventPublisher()
        val useCase = ReceiveTradeUseCase(repository, publisher)

        val command = TradeFixtures.sampleTradeCommand()

        useCase(command)

        assertTrue(publisher.events.first() is TradeReceived)
    }

    @Test
    fun `should ignore duplicate trades`() = runBlocking {
        val repository = InMemoryTradeRepository()
        val publisher = InMemoryDomainEventPublisher()
        val useCase = ReceiveTradeUseCase(repository, publisher)

        val command = TradeFixtures.sampleTradeCommand()

        useCase(command)
        useCase(command) // Duplicate trade

        assertEquals(1, repository.count()) // Only one event should be published
    }

}