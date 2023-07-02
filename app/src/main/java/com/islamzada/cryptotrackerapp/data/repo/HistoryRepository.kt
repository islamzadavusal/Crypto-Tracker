package com.islamzada.cryptotrackerapp.data.repo

import com.islamzada.cryptotrackerapp.data.datasource.HistoryDataSource
import com.islamzada.cryptotrackerapp.data.models.PriceModel

class HistoryRepository(private val ds: HistoryDataSource) {

    suspend fun getAllHistories(): List<PriceModel>? =
        ds.getAllHistories()

    suspend fun getAllHistoriesByCurrencyId(currencyId: String): List<PriceModel>? =
        ds.getAllHistoriesByCurrencyId(currencyId)

    suspend fun insertHistory(HistoryModel: PriceModel) =
        ds.insertHistory(HistoryModel)


    suspend fun deleteHistory(HistoryModel: PriceModel) =
        ds.deleteHistory(HistoryModel)

}