package com.interview.stockwatcher

import java.math.BigDecimal

object SampleData {
    val sampleStocks = listOf(
        Stock(ticker = "GOOG", name = "Alphabet Inc.", currentPrice = BigDecimal("172.56"), priceChange = BigDecimal("1.0")),        Stock(ticker = "AAPL", name = "Apple Inc.", currentPrice = BigDecimal("214.29"), priceChange = BigDecimal("-1.0")),        Stock(ticker = "MSFT", name = "Microsoft Corporation", currentPrice = BigDecimal("449.78"), priceChange = BigDecimal("0.0"))    )
}