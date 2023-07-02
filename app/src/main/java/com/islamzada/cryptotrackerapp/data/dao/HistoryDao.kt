package com.islamzada.cryptotrackerapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.islamzada.cryptotrackerapp.data.models.PriceModel

@Dao
interface HistoryDao {

    // Query to retrieve all histories from the price_table
    @Query("SELECT * FROM price_table")
    suspend fun getAllHistories(): List<PriceModel>?

    // Query to retrieve all histories filtered by currencyId from the price_table
    @Query("SELECT * FROM price_table WHERE currencyId = :currencyId")
    suspend fun getAllHistoriesByCurrencyId(currencyId: String): List<PriceModel>?

    // Insert a history into the price_table
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHistory(historyModel: PriceModel)

    // Delete a history from the price_table
    @Delete
    suspend fun deleteHistory(historyModel: PriceModel)
}
