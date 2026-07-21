package org.example.trade.domain.event

interface DomainEventPublisher {

    fun publish(event: DomainEvent)
}