package org.example.trade.infrastructure.repository.postgres

import org.springframework.r2dbc.core.DatabaseClient

object TradeRepositoryFactory {
    fun create(): PostgresTradeRepository {
        val connectionFactory = PostgresConnection.create()
        val databaseClient = DatabaseClient.create(connectionFactory)

        return PostgresTradeRepository(databaseClient)
    }
}