package trade.infrastructure.repository.postges

import kotlinx.coroutines.runBlocking
import org.example.trade.infrastructure.repository.postgres.PostgresTradeRepository
import org.example.trade.testsupport.TradeFixtures
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class PostgresTradeRepositoryTest : PostgresIntegrationTest() {

    private lateinit var repository: PostgresTradeRepository

    @BeforeEach
    fun setupRepository() {
        repository = PostgresTradeRepository(databaseClient)
    }

    @Test
    fun `should start postgres container`() {
        assert(postgres.isRunning)
    }

    @Test
    fun `should save and retrieve a trade`() = runBlocking {
        // Given
        val trade = TradeFixtures.sampleTrade()

        // When
        repository.save(trade)
        val retrievedTrade = repository.findById(trade.id)

        // Then
        assertNotNull(retrievedTrade)
        assertEquals(trade, retrievedTrade)
    }
}