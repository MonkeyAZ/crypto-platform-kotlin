package org.example.trade.testsupport

import org.example.trade.application.ReceiveTrade
import org.example.trade.application.ReceiveTradeCommand

class FakeReceiveTrade : ReceiveTrade {

    val commands = mutableListOf<ReceiveTradeCommand>()

    override suspend fun invoke(command: ReceiveTradeCommand) {
        commands.add(command)
    }
}
