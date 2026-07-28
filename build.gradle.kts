plugins {
    kotlin("jvm") version "2.3.21"
    kotlin("plugin.serialization") version "2.3.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.7.3")
    implementation("io.ktor:ktor-client-core:3.2.3")
    implementation("io.ktor:ktor-client-cio:3.2.3")
    implementation("io.ktor:ktor-client-websockets:3.2.3")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.2.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")
    implementation("org.postgresql:r2dbc-postgresql:1.0.7.RELEASE")
    implementation("org.postgresql:postgresql:42.7.4")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc:3.3.4")
    implementation("org.flywaydb:flyway-core:10.5.0")
    implementation("org.flywaydb:flyway-database-postgresql:10.5.0")
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.11.0")
    testImplementation("org.testcontainers:testcontainers-junit-jupiter:2.0.2")
    testImplementation("org.testcontainers:testcontainers-postgresql:2.0.2")
    testImplementation("io.r2dbc:r2dbc-spi")
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}