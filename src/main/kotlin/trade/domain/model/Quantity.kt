package org.example.trade.domain.model

import java.math.BigDecimal

@JvmInline
value class Quantity (val value: BigDecimal) {

    init {
        require(value > BigDecimal.ZERO) { "Quantity cannot be negative" }
    }
}