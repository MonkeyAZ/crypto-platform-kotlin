package org.example.trade.domain.model

import java.math.BigDecimal
import kotlin.require

@JvmInline
value class Price (val value: BigDecimal) {

    init {
        require(value > BigDecimal.ZERO) { "Price cannot be negative" }
    }
}