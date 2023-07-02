package com.islamzada.cryptotrackerapp.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkDataSource (private val ns: NetworkService) {

    suspend fun getAllCryptos() = withContext(Dispatchers.IO){
        ns.getCryptoCurrencyById()
    }

}