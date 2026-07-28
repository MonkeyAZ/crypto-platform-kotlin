package org.example.trade.infrastructure.binance.connection

import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import kotlinx.io.IOException
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

class ConnectionSupervisor(
    private val delegate: BinanceConnection,
    private val retryDelay: Duration = 1.seconds
) {
    fun messages(): Flow<String> = flow {
        while(currentCoroutineContext().isActive) {

            try {
                emitAll(delegate.connect())
            } catch (ex: IOException) {
                delay(retryDelay)
            }
        }
    }
}