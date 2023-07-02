package com.islamzada.cryptotrackerapp.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.islamzada.cryptotrackerapp.data.models.ExtendedCurrencyModel
import com.islamzada.cryptotrackerapp.data.models.PriceModel
import com.islamzada.cryptotrackerapp.data.notification.NotificationService
import com.islamzada.cryptotrackerapp.data.notification.NotificationWork
import com.islamzada.cryptotrackerapp.databinding.ActivityMainBinding
import com.islamzada.cryptotrackerapp.ui.viewmodel.HistoryViewModel
import com.islamzada.cryptotrackerapp.ui.viewmodel.MainViewModel
import com.islamzada.cryptotrackerapp.ui.viewmodel.MaxMinViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private val historyViewModel: HistoryViewModel by viewModels()
    private val maxMinViewModel: MaxMinViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // Starting the NotificationService and observing live data
        Intent(this, NotificationService::class.java).also {
            startService(it)
            observeLiveData()
        }
    }

    private fun observeLiveData() {
        // Observing the cryptoLiveData from the mainViewModel
        mainViewModel.cryptoLiveData.observe(this) { currencies ->
            // Observing the previousHistoriesLiveData from the historyViewModel
            historyViewModel.previousHistoriesLiveData.observe(this) { histories ->
                val pricesHistoryName = histories.map { it.currencyName }

                // Checking if Bitcoin is in the price history
                if (pricesHistoryName.contains("Bitcoin")) {
                    priceNotificationSender("Bitcoin", histories, currencies)
                }

                // Checking if Ethereum is in the price history
                if (pricesHistoryName.contains("Ethereum")) {
                    priceNotificationSender("Ethereum", histories, currencies)
                }

                // Checking if Ripple is in the price history
                if (pricesHistoryName.contains("Ripple")) {
                    priceNotificationSender("Ripple", histories, currencies)
                }
            }
        }
    }

    private fun priceNotificationSender(
        title: String,
        histories: List<PriceModel>,
        currencies: List<ExtendedCurrencyModel>
    ) {
        // Finding the maximum price for the given currency
        val maxPrice = histories.filter { it.currencyName == title && it.currencyId == title.lowercase() }
            .maxOf { it.maxPrice }

        // Finding the minimum price for the given currency
        val minPrice = histories.filter { it.currencyName == title && it.currencyId == title.lowercase() }
            .minOf { it.minPrice }

        // Finding the currency model for the given title
        val currency = currencies.filter { it.title == title }[0]

        // Checking if the max price is lower than the current price
        if (maxPrice < currency.currencyModel.usd) {
            NotificationWork.activateWorkManager(
                this,
                "Max price alert",
                "$title reached to maximum price. Please check the app."
            )
            maxMinViewModel.deletePrice(histories.filter { it.maxPrice == maxPrice }[0])
        }
        // Checking if the min price is higher than the current price
        else if (minPrice > currency.currencyModel.usd) {
            NotificationWork.activateWorkManager(
                this,
                "Min price alert",
                "$title fallen to minimum price. Please check the app."
            )
            maxMinViewModel.deletePrice(histories.filter { it.minPrice == minPrice }[0])
        }
    }
}