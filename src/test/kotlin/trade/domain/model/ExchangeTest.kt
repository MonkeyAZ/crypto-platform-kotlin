package trade.domain.model

import org.example.trade.domain.model.Exchange
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ExchangeTest {

    @Test
    fun `should create a valid exchange`() {
        val exchange = Exchange("Binance")

        assertEquals("Binance", exchange.value)
    }

    @Test
    fun `blank exchange should fail`() {
        assertFailsWith<IllegalArgumentException> {
            Exchange("")
        }
    }
}