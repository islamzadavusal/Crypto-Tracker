package com.islamzada.cryptotrackerapp.di

import android.content.Context
import androidx.room.Room
import com.islamzada.cryptotrackerapp.data.dao.HistoryDao
import com.islamzada.cryptotrackerapp.data.database.AppDatabase
import com.islamzada.cryptotrackerapp.data.datasource.HistoryDataSource
import com.islamzada.cryptotrackerapp.data.repo.HistoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // This module provides dependencies related to the database.

    // Provides an instance of the AppDatabase by using Room.
    // The provided instance is a singleton.
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context = context, klass = AppDatabase::class.java, "app_database")
            .build()

    // Provides an instance of the HistoryDao by using the AppDatabase.
    @Provides
    @Singleton
    fun provideHistoryDao(db: AppDatabase) = db.getHistoryDao()

    // Provides an instance of the HistoryDataSource by using the HistoryDao.
    @Provides
    @Singleton
    fun provideHistoryDataSource(dao: HistoryDao) = HistoryDataSource(dao)

    // Provides an instance of the HistoryRepository by using the HistoryDataSource.
    @Provides
    @Singleton
    fun provideHistoryRepository(ds: HistoryDataSource) = HistoryRepository(ds)
}