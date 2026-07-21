package trade.domain.model

import org.example.trade.domain.model.Exchange
import org.example.trade.domain.model.Price
import org.example.trade.domain.model.Quantity
import org.example.trade.domain.model.Symbol
import org.example.trade.domain.model.Trade
import org.example.trade.domain.model.TradeId
import org.junit.jupiter.api.Test
import java.time.Instant
import kotlin.test.assertEquals

class TradeTest {

    @Test
    fun `should create a valid trade`() {
        val trade = Trade.receive(
            id = TradeId("12345"),
            symbol = Symbol("BTCUSDT"),
            price = Price("123.45".toBigDecimal()),
            quantity = Quantity("0.01".toBigDecimal()),
            exchange = Exchange("Binance"),
            timestamp = Instant.now()
        )

        assertEquals("12345", trade.id.value)
        assertEquals("BTCUSDT", trade.symbol.value)
        assertEquals("123.45".toBigDecimal(), trade.price.value)
        assertEquals("0.01".toBigDecimal(), trade.quantity.value)
        assertEquals("Binance", trade.exchange.value)
    }

    @Test
    fun `receiving trade should raise TradeReceived event`() {
        val trade = Trade.receive(
            id = TradeId("12345"),
            symbol = Symbol("BTCUSDT"),
            price = Price("123.45".toBigDecimal()),
            quantity = Quantity("0.01".toBigDecimal()),
            exchange = Exchange("Binance"),
            timestamp = Instant.now()
        )

        val events = trade.domainEvents()
        assertEquals(1, events.size)
    }
}