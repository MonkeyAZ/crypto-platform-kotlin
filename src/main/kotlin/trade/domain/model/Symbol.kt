package org.example.trade.domain.model

@JvmInline
value class Symbol(private val raw: String) {

    val value: String
        get() = raw.uppercase()

    init {
        require(value.isNotBlank()) { "Symbol cannot be blank" }
    }
}
