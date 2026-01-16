package com.interview.stockwatcher

import java.math.BigDecimal

data class Stock(
    val ticker: String,
    val name: String,
    val currentPrice: BigDecimal,
    val priceChange: BigDecimal
)
