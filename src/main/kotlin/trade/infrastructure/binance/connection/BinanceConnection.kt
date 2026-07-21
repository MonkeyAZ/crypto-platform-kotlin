package org.example.trade.infrastructure.binance.connection

import io.ktor.websocket.Frame
import kotlinx.coroutines.flow.Flow

interface BinanceConnection {
    fun incomingFrames(): Flow<Frame.Text>
}