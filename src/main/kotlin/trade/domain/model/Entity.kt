package org.example.trade.domain.model

import org.example.trade.domain.event.DomainEvent

abstract class Entity {
    private val events = mutableListOf<DomainEvent>()

    protected fun raise(event: DomainEvent) {
        events.add(event)
    }

    fun domainEvents(): List<DomainEvent> = events.toList()

    fun clearEvents() {
        events.clear()
    }
}