package trade.domain.model

import org.example.trade.domain.model.Symbol
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class SymbolTest {

    @Test
    fun `should create a valid symbol`() {
        val symbol = Symbol("BTCUSDT")

        assertEquals("BTCUSDT", symbol.value)
    }

    @Test
    fun `blank symbol should fail`() {
        assertFailsWith<IllegalArgumentException> {
            Symbol("")
        }
    }

    @Test
    fun `symbol should always be uppercase`() {
        val symbol = Symbol("btcusdt")

        assertEquals("BTCUSDT", symbol.value)
    }
}