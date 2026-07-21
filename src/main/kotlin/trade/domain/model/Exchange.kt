package org.example.trade.domain.model

@JvmInline
value class Exchange (val value: String) {

    init {
        require(value.isNotBlank()) { "Exchange cannot be blank" }
    }
}