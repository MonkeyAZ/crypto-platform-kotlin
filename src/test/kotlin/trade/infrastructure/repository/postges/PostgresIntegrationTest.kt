package trade.infrastructure.repository.postges

import org.flywaydb.core.Flyway
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import org.springframework.r2dbc.core.DatabaseClient
import org.testcontainers.postgresql.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class PostgresIntegrationTest {

    companion object {
        @Container
        @JvmStatic
        val postgres = PostgreSQLContainer("postgres:17")
    }

    protected lateinit var databaseClient: DatabaseClient

    @BeforeAll
    fun runMigrations() {

        Flyway.configure()
            .dataSource(
                postgres.jdbcUrl,
                postgres.username,
                postgres.password
            )
            .load()
            .migrate()
        databaseClient = TestDatabase.create(postgres)
    }

    @BeforeEach
    fun cleanTables() {
        databaseClient.sql("TRUNCATE TABLE trades").fetch().rowsUpdated()
    }
}