package trade.domain.model

import org.example.trade.domain.model.Quantity
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class QuantityTest {

    @Test
    fun `should create a valid quantity`() {
        val quantity = Quantity(BigDecimal("10.5"))

        assertEquals(BigDecimal("10.5"), quantity.value)
    }

    @Test
    fun `should fail for negative quantity`() {
        assertFailsWith<IllegalArgumentException> {
            Quantity(BigDecimal("-1.00"))
        }
    }
}