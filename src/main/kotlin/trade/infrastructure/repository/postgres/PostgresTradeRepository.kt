package org.example.trade.infrastructure.repository.postgres

import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.example.trade.domain.model.Trade
import org.example.trade.domain.model.TradeId
import org.example.trade.domain.repository.TradeRepository
import org.springframework.r2dbc.core.DatabaseClient

class PostgresTradeRepository(private val client: DatabaseClient) : TradeRepository{
    override suspend fun save(trade: Trade) {
        client.sql(
            """
            INSERT INTO trades (id, symbol, quantity, exchange, price, timestamp)
            VALUES (:id, :symbol, :quantity, :exchange, :price, :timestamp)
            """
        ).bind("id", trade.id.value)
            .bind("symbol", trade.symbol.value)
            .bind("quantity", trade.quantity.value)
            .bind("exchange", trade.exchange.value)
            .bind("price", trade.price.value)
            .bind("timestamp", trade.timestamp)
            .fetch()
            .rowsUpdated()
            .awaitSingle()
    }

    override suspend fun exists(id: TradeId): Boolean {
        return client.sql(
            """
            SELECT COUNT(*) FROM trades WHERE id = :id
            """
        ).bind("id", id.value)
            .map { row, _ -> row.get(0, Boolean::class.java)!! }
            .one()
            .awaitSingle()
    }

    override suspend fun findById(id: TradeId): Trade? {
        return client.sql(
            """
            SELECT * FROM trades WHERE id = :id
            """
        ).bind("id", id.value)
            .map { row, _ -> TradeRowMapper.map(row) }
            .one()
            .awaitSingleOrNull()
    }
}