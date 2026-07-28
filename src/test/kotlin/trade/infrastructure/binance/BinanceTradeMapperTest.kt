package trade.infrastructure.binance

import org.example.trade.infrastructure.binance.BinanceTradeEvent
import org.example.trade.infrastructure.binance.BinanceTradeMapper
import org.junit.jupiter.api.Test

class BinanceTradeMapperTest {

    @Test
    fun `should map Binance trade event to market trade message`() {
        val event = BinanceTradeEvent(
            eventTime = 123456789L,
            symbol = "BTCUSDT",
            tradeId = 12345L,
            price = "50000.00",
            quantity = "0.001"
        )

        val mapper = BinanceTradeMapper()
        val marketTradeMessage = mapper.toMarketTrade(event)

        assert(marketTradeMessage.id == "12345")
        assert(marketTradeMessage.symbol == "BTCUSDT")
        assert(marketTradeMessage.price == "50000.00".toBigDecimal())
        assert(marketTradeMessage.quantity == 0.001.toBigDecimal())
        assert(marketTradeMessage.exchange == "binance")
        assert(marketTradeMessage.timestamp.toEpochMilli() == 123456789L)
    }
}