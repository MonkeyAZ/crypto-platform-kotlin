package org.example.trade.infrastructure.repository.postgres

import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import io.r2dbc.spi.Option

object PostgresConnection {

    fun create(
        host: String = "localhost",
        port: Int = 5432,
        database: String = "crypto",
        username: String = "crypto",
        password: String = "crypto"
    ): ConnectionFactory {
        return ConnectionFactories.get(
            ConnectionFactoryOptions.builder()
                .option(ConnectionFactoryOptions.DRIVER, "postgresql")
                .option(ConnectionFactoryOptions.HOST, host)
                .option(ConnectionFactoryOptions.PORT, port)
                .option(ConnectionFactoryOptions.DATABASE, database)
                .option(ConnectionFactoryOptions.USER, username)
                .option(ConnectionFactoryOptions.PASSWORD, password)
                .option(Option.valueOf<String>("sslMode"), "disable")
                .build()
        )
    }
}