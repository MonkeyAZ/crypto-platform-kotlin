package org.example.trade.infrastructure.repository.postgres

import org.flywaydb.core.Flyway

object FlywayMigration {

    fun runMigration(
        host: String = "localhost",
        port: Int = 5432,
        database: String = "crypto",
        username: String = "crypto",
        password: String = "crypto"
    ) {
        val jdbcUrl = "jdbc:postgresql://$host:$port/$database"

        Flyway.configure()
            .dataSource(jdbcUrl, username, password)
            .load()
            .migrate()
    }
}