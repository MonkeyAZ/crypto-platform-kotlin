package org.example.trade.testsupport

import kotlinx.coroutines.flow.Flow
import org.example.trade.infrastructure.binance.connection.BinanceConnection

class FakeBinanceConnection (vararg connections: Flow<String>) : BinanceConnection {

    private val queue = ArrayDeque(connections.toList())

    override fun connect(): Flow<String> {
        if (queue.isEmpty()) {
            throw IllegalStateException("No more connections to emit")
        }

        return queue.removeFirst()
    }
}