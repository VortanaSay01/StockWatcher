package com.interview.stockwatcher

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.interview.stockwatcher.ui.theme.StockWatcherTheme
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * This Composable screen contains INTENTIONAL FLAWS for the interview exercise.
 *
 * TODO: For the candidate: Review this file and discuss potential performance and style issues.
 *  - When scrolling or tapping items, the UI is janky and unresponsive. What is the primary cause of this?
 *  - Examine the `StockItem` composable. Are there any inputs or operations that could lead to unnecessary recompositions or performance bottlenecks?
 *  - How could the `StockItem` be made more robust and performant?
 */
@Composable
fun StockListScreen(
    stocks: List<Stock>,
    isLoading: Boolean,
    error: String?,
    onShuffleClicked: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) { // Changed from Box to Column
        Button(
            onClick = onShuffleClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Shuffle List")
        }
        Box(modifier = Modifier.fillMaxSize()) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (error != null) {
                Text(
                    text = error,
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.error
                )
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(stocks) { stock ->
                        StockItem(stock = stock)
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}

@Composable
fun StockItem(stock: Stock, modifier: Modifier = Modifier) {
    val derivedValue = performCalculation(stock.ticker)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { println("Clicked ${stock.ticker} - $derivedValue") }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stock.ticker,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stock.name,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
        Text(
            text = "$${stock.currentPrice}",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

private fun performCalculation(input: String): Double {
    var result = input.length.toDouble()
    for (i in 0..2_000_000) {
        result += sin(result) * cos(i.toDouble())
    }
    return sqrt(result)
}

@Preview(showBackground = true)
@Composable
fun StockListScreenPreview() {
    StockWatcherTheme {
        StockListScreen(
            stocks = SampleData.sampleStocks,
            isLoading = false,
            error = null,
            onShuffleClicked = {})
    }
}
