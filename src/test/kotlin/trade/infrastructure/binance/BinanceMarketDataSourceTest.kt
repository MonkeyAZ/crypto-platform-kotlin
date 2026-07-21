package trade.infrastructure.binance

import io.ktor.websocket.Frame
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.example.trade.infrastructure.binance.BinanceMarketDataSource
import org.example.trade.infrastructure.binance.BinanceTradeDeserializer
import org.example.trade.infrastructure.binance.BinanceTradeMapper
import org.example.trade.testsupport.FakeBinanceConnection
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import kotlin.test.assertEquals

class BinanceMarketDataSourceTest {

    @Test
    fun `should emit market trade from Binance trade event`() = runBlocking {
        val connection = FakeBinanceConnection(
            Frame.Text(
                """
                {
                    "e": "trade",
                    "E": 123456789,
                    "s": "BTCUSDT",
                    "t": 12345,
                    "p": "50000.00",
                    "q": "0.001"
                }
                """.trimIndent()
            )
        )

        val source = BinanceMarketDataSource(
            connection = connection,
            deserializer = BinanceTradeDeserializer(
                json = Json {
                    ignoreUnknownKeys = true
                }
            ),
            mapper = BinanceTradeMapper(),
        )

        source.trades().collect { trade ->
            assertEquals("12345", trade.id)
            assertEquals("BTCUSDT", trade.symbol)
            assertEquals(BigDecimal("50000.00"), trade.price)
            assertEquals(BigDecimal("0.001"), trade.quantity)
            assertEquals("binance", trade.exchange)
            assertEquals(123456789L, trade.timestamp.toEpochMilli())
        }
    }
}