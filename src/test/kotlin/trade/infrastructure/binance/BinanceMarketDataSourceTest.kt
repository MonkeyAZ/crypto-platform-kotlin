package trade.infrastructure.binance

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.example.trade.infrastructure.binance.BinanceMarketDataSource
import org.example.trade.infrastructure.binance.BinanceTradeDeserializer
import org.example.trade.infrastructure.binance.BinanceTradeMapper
import org.example.trade.infrastructure.binance.connection.ConnectionSupervisor
import org.example.trade.testsupport.FakeBinanceConnection
import org.junit.jupiter.api.Test
import java.io.IOException
import kotlin.test.assertEquals

class BinanceMarketDataSourceTest {

    @Test
    fun `should emit market trade from Binance trade event`() = runBlocking {
        val connection = ConnectionSupervisor(
                FakeBinanceConnection(
                flow {
                    emit(
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
                }
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

        val trades = source.trades()
            .take(1)
            .toList()

        assertEquals(1, trades.size)
        assertEquals("12345", trades[0].id)
    }

    @Test
    fun `should reconnect after connection loss`() = runBlocking {
        val firstConnection = flow {
            emit(
                """
                {
                    "e": "trade",
                    "E": 123456789,
                    "s": "BTCUSDT",
                    "t": 1,
                    "p": "50000.00",
                    "q": "0.001"
                }
                """.trimIndent()
            )

            throw IOException("Connection lost")
        }

        val secondConnection = flow {
            emit(
                """
                {
                    "e": "trade",
                    "E": 123456789,
                    "s": "BTCUSDT",
                    "t": 2,
                    "p": "50000.00",
                    "q": "0.001"
                }
                """.trimIndent()
            )
        }

        val connection = ConnectionSupervisor(
            FakeBinanceConnection(
                firstConnection,
                secondConnection
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

        val trades = source.trades()
            .take(2)
            .toList()

        assertEquals(2, trades.size)
        assertEquals("1", trades[0].id)
        assertEquals("2", trades[1].id)
    }
}