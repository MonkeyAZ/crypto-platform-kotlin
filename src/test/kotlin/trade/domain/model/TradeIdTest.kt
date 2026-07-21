package trade.domain.model

import org.example.trade.domain.model.TradeId
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class TradeIdTest {

    @Test
    fun `should create a valid trade id`() {
        val tradeId = TradeId("12345")

        assertEquals("12345", tradeId.value)
    }

    @Test
    fun `blank trade id should fail`() {
        assertFailsWith<IllegalArgumentException> {
            TradeId("")
        }
    }
}