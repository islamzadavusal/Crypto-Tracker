package com.islamzada.cryptotrackerapp.data.datasource

import com.islamzada.cryptotrackerapp.data.dao.HistoryDao
import com.islamzada.cryptotrackerapp.data.models.PriceModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HistoryDataSource(private val dao: HistoryDao) {

    // Get all histories from the DAO
    suspend fun getAllHistories(): List<PriceModel>? = withContext(Dispatchers.IO) {
        dao.getAllHistories()
    }

    // Get all histories filtered by currencyId from the DAO
    suspend fun getAllHistoriesByCurrencyId(currencyId: String): List<PriceModel>? = withContext(Dispatchers.IO) {
        dao.getAllHistoriesByCurrencyId(currencyId)
    }

    // Insert a history into the DAO
    suspend fun insertHistory(historyModel: PriceModel) = withContext(Dispatchers.IO) {
        dao.insertHistory(historyModel)
    }

    // Delete a history from the DAO
    suspend fun deleteHistory(historyModel: PriceModel) = withContext(Dispatchers.IO) {
        dao.deleteHistory(historyModel)
    }
}
