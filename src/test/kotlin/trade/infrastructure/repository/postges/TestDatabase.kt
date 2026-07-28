package trade.infrastructure.repository.postges

import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactoryOptions
import org.springframework.r2dbc.core.DatabaseClient
import org.testcontainers.postgresql.PostgreSQLContainer

object TestDatabase {

    fun create(postgres: PostgreSQLContainer): DatabaseClient {

        val connectionFactory = ConnectionFactories.get(
            ConnectionFactoryOptions.builder()
                .option(ConnectionFactoryOptions.DRIVER, "postgresql")
                .option(ConnectionFactoryOptions.HOST, postgres.host)
                .option(ConnectionFactoryOptions.PORT, postgres.firstMappedPort)
                .option(ConnectionFactoryOptions.DATABASE, postgres.databaseName)
                .option(ConnectionFactoryOptions.USER, postgres.username)
                .option(ConnectionFactoryOptions.PASSWORD, postgres.password)
                .build()
        )

        return DatabaseClient.create(connectionFactory)
    }
}