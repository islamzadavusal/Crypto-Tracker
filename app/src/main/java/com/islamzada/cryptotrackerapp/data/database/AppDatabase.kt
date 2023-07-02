package com.islamzada.cryptotrackerapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.islamzada.cryptotrackerapp.data.dao.HistoryDao
import com.islamzada.cryptotrackerapp.data.models.PriceModel

@Database(entities = [PriceModel::class], version = 1)

abstract class AppDatabase : RoomDatabase(){
    abstract fun getHistoryDao(): HistoryDao
}