package com.interview.stockwatcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.interview.stockwatcher.ui.theme.StockWatcherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: StockListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Load the initial data only on the first creation
        if (savedInstanceState == null) {
            viewModel.loadStocks()
        }

        setContent {
            StockWatcherTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    StockListRoute(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun StockListRoute(viewModel: StockListViewModel) {
    val stocks by viewModel.stocks.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    StockListScreen(
        stocks = stocks,
        isLoading = isLoading,
        error = error,
        onShuffleClicked = { viewModel.shuffleStockList() }
    )
}
