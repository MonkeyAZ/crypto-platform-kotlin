package trade.domain.model

import org.example.trade.domain.model.Price
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class PriceTest {

    @Test
    fun `should create a valid price`() {
        val price = Price(BigDecimal("123.45"))

        assertEquals(BigDecimal("123.45"), price.value)
    }

    @Test
    fun `should fail for negative price`() {
        assertFailsWith<IllegalArgumentException> {
            Price(BigDecimal("-1.00"))
        }
    }
}