package com.interview.stockwatcher

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * This ViewModel contains INTENTIONAL FLAWS for the interview exercise.
 *
 * TODO: For the candidate: Review this ViewModel's architecture and identify potential issues.
 *  - Examine how state is exposed to the UI. What are the risks or drawbacks of this approach?
 *  - Analyze the way asynchronous operations are handled in `loadStocks()`. How does this impact
 *    the codebase's testability and adherence to modern Android best practices?
 */
@HiltViewModel
class StockListViewModel @Inject constructor(
    private val stockRepository: StockRepository
) : ViewModel() {

    private val _stocks = MutableStateFlow<List<Stock>>(emptyList())
    val stocks: StateFlow<List<Stock>> = _stocks.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun loadStocks() {
        _isLoading.value = true
        _error.value = null

        stockRepository.fetchStockData(object : StockRepository.StockCallback {
            override fun onSuccess(stocks: List<Stock>) {
                _stocks.value = stocks
                _isLoading.value = false
            }

            override fun onError(e: Exception) {
                _error.value = "Failed to load data. Please try again."
                _isLoading.value = false
            }
        })
    }

    fun shuffleStockList() {
        _stocks.value = _stocks.value.shuffled().map { it.copy() }
    }
}
