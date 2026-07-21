package org.example.trade.testsupport

import io.ktor.websocket.Frame
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.example.trade.infrastructure.binance.connection.BinanceConnection

class FakeBinanceConnection (private val frames: Frame.Text) : BinanceConnection {
    override fun incomingFrames(): Flow<Frame.Text> = flowOf(frames)
}