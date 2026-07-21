package org.example.trade.infrastructure.event

import org.example.trade.domain.event.DomainEvent
import org.example.trade.domain.event.DomainEventPublisher

class InMemoryDomainEventPublisher : DomainEventPublisher {

    val events = mutableListOf<DomainEvent>()

    override fun publish(event: DomainEvent) {
        println(event)
        events.add(event)
    }
}