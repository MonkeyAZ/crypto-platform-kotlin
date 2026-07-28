package org.example.trade.infrastructure.repository.postgres

import io.r2dbc.spi.Row
import org.example.trade.domain.model.Exchange
import org.example.trade.domain.model.Price
import org.example.trade.domain.model.Quantity
import org.example.trade.domain.model.Symbol
import org.example.trade.domain.model.Trade
import org.example.trade.domain.model.TradeId
import java.math.BigDecimal

object TradeRowMapper {

    fun map(row: Row): Trade {
        return Trade.receive(
            TradeId(row.get("id", String::class.java)!!),
            Symbol(row.get("symbol", String::class.java)!!),
            Price(row.get("price", BigDecimal::class.java)!!),
            Quantity(row.get("quantity", BigDecimal::class.java)!!),
            Exchange(row.get("exchange", String::class.java)!!),
            row.get("timestamp", java.time.Instant::class.java)!!
        )
    }
}