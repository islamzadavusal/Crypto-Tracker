package com.islamzada.cryptotrackerapp.data.network

import com.islamzada.cryptotrackerapp.data.repo.NetworkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private val BASE_URL = "https://api.coingecko.com/"

    @Provides
    @Singleton
    fun provideConfig(): NetworkService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NetworkService::class.java)

    @Provides
    @Singleton
    fun provideDataSource(ns: NetworkService) = NetworkDataSource(ns)

    @Provides
    @Singleton
    fun provideRepository(nds: NetworkDataSource) = NetworkRepository(nds)
}