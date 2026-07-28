package org.example.trade.infrastructure.binance.connection

import kotlinx.coroutines.flow.Flow

interface BinanceConnection {
    fun connect(): Flow<String>
}