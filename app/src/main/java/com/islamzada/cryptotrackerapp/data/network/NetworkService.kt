package com.islamzada.cryptotrackerapp.data.network

import com.islamzada.cryptotrackerapp.data.models.CurrenciesList
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("api/v3/simple/price")

    suspend fun getCryptoCurrencyById(
        @Query("ids") id: String = "bitcoin,ripple,ethereum",
        @Query("vs_currencies") date: String = "usd",
        @Query("include_market_cap") includeMarketCap: Boolean = true,
        @Query("include_24hr_vol") include24hrVol: Boolean = true,
        @Query("include_24hr_change") include24hrChange: Boolean = true,
        @Query("include_last_updated_at") includeLastUpdatedAt: Boolean = true,
        @Query("precision") precision: Int = 1,
    ): CurrenciesList?

}