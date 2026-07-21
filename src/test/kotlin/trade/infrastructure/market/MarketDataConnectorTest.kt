package trade.infrastructure.market

import kotlinx.coroutines.runBlocking
import org.example.trade.application.MarketTradeHandler
import org.example.trade.testsupport.FakeMarketDataSource
import org.example.trade.testsupport.FakeReceiveTrade
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MarketDataConnectorTest {

    @Test
    fun `should invoke receive trade use case`() = runBlocking {
        val marketDataSource = FakeMarketDataSource()

        val receiveTrade = FakeReceiveTrade()

        val connector = MarketTradeHandler(marketDataSource, receiveTrade)

        connector.start()

        assertEquals(5, receiveTrade.commands.size)
    }
}