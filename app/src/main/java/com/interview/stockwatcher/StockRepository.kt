package com.interview.stockwatcher

import android.os.Handler
import android.os.Looper
import java.math.BigDecimal
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This is the INTENTIONALLY FLAWED class for the interview exercise.
 * It simulates fetching data using an old, callback-based approach with manual threading.
 *
 * TODO: For the candidate: Review this class and its interaction with the broader system.
 *  - What are the architectural drawbacks of exposing data via a callback interface?
 *  - How does this implementation impact testability and developer ergonomics?
 *  - Propose a strategy to modernize this API for a coroutine-first codebase.
 */
@Singleton
class StockRepository @Inject constructor() {

    private val executor: ExecutorService = Executors.newSingleThreadExecutor()
    private val resultHandler = Handler(Looper.getMainLooper())

    /**
     * A classic callback interface for asynchronous results.
     */
    interface StockCallback {
        fun onSuccess(stocks: List<Stock>)
        fun onError(e: Exception)
    }

    /**
     * Fetches stock data asynchronously and returns the result via a callback.
     * This method simulates a slow network request.
     *
     * @param callback The callback to be invoked on the main thread with the result.
     */
    fun fetchStockData(callback: StockCallback) {
        executor.execute {
            try {
                // Simulate a 2-second network delay
                Thread.sleep(2000)

                // Create some fake stock data
                val fakeData = (1..200).map { i ->
                    Stock(
                        ticker = "TICKER$i",
                        name = "Company Name $i, Inc.",
                        currentPrice = BigDecimal.valueOf(100.0 + i),
                        priceChange = BigDecimal.valueOf((i % 10 - 5) * 0.5) // Some positive/negative change
                    )
                }

                // Post the successful result back to the main thread
                resultHandler.post { callback.onSuccess(fakeData) }

            } catch (e: InterruptedException) {
                // In case the thread is interrupted, post an error back to the main thread
                Thread.currentThread().interrupt()
                resultHandler.post { callback.onError(e) }
            }
        }
    }
}
