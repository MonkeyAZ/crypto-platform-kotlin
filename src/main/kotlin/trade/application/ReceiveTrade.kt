package org.example.trade.application

interface ReceiveTrade {
    suspend operator fun invoke(command: ReceiveTradeCommand)
}