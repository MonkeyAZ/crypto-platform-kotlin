package org.example.trade.domain.model

@JvmInline
value class TradeId (val value: String) {

    init {
        require(value.isNotBlank()) { "TradeId cannot be blank" }
    }
}