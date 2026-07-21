package org.example.trade.domain.event

import org.example.trade.domain.model.Trade

class TradeReceived (val trade: Trade) : DomainEvent{
}