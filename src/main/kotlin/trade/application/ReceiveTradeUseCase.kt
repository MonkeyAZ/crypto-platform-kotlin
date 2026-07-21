package org.example.trade.application

import org.example.trade.domain.event.DomainEventPublisher
import org.example.trade.domain.model.Exchange
import org.example.trade.domain.model.Price
import org.example.trade.domain.model.Quantity
import org.example.trade.domain.model.Symbol
import org.example.trade.domain.model.Trade
import org.example.trade.domain.model.TradeId
import org.example.trade.domain.repository.TradeRepository

class ReceiveTradeUseCase(
    private val repository: TradeRepository,
    private val publisher: DomainEventPublisher
) : ReceiveTrade {

    override suspend operator fun invoke(command: ReceiveTradeCommand) {
        val id = TradeId(command.id)

        if (repository.exists(id)) {
            return
        }



        val trade = Trade.receive(
            id = TradeId(command.id),
            symbol = Symbol(command.symbol),
            exchange = Exchange(command.exchange),
            price = Price(command.price),
            quantity = Quantity(command.quantity),
            timestamp = command.timestamp
        )

        repository.save(trade)

        trade.domainEvents().forEach { event ->
            publisher.publish(event)
        }

        trade.clearEvents()
    }
}