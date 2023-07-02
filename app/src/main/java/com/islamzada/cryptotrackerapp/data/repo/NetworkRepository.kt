package com.islamzada.cryptotrackerapp.data.repo

import com.islamzada.cryptotrackerapp.data.network.NetworkDataSource

class NetworkRepository(private val nds: NetworkDataSource) {
    suspend fun getAllCryptos() = nds.getAllCryptos()
}