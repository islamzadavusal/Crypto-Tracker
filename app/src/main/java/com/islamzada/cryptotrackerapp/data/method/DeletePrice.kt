package com.islamzada.cryptotrackerapp.data.method

import com.islamzada.cryptotrackerapp.data.models.PriceModel
import com.islamzada.cryptotrackerapp.data.repo.HistoryRepository
import javax.inject.Inject

class DeletePrice @Inject constructor(
    private val historyRepository: HistoryRepository) {

    // DeletePrice function to delete a price from the history repository
    suspend operator fun invoke(historyModel: PriceModel) {
        historyRepository.deleteHistory(historyModel)
    }
}
