package com.islamzada.cryptotrackerapp.data.method

import com.islamzada.cryptotrackerapp.data.models.PriceModel
import com.islamzada.cryptotrackerapp.data.repo.HistoryRepository
import javax.inject.Inject

class InsertPrice @Inject constructor(
    private val priceHistory: HistoryRepository) {

    // Invoke function to insert a price history into the history repository
    suspend operator fun invoke(HistoryModel: PriceModel) {
        // Insert the provided price history model into the history repository
        priceHistory.insertHistory(HistoryModel)
    }
}
