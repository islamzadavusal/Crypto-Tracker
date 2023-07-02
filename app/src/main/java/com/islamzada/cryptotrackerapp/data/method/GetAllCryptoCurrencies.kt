package com.islamzada.cryptotrackerapp.data.method

import androidx.lifecycle.MutableLiveData
import com.islamzada.cryptotrackerapp.data.models.ExtendedCurrencyModel
import com.islamzada.cryptotrackerapp.data.repo.NetworkRepository
import com.islamzada.cryptotrackerapp.message.Resource
import javax.inject.Inject

class GetAllCryptoCurrencies @Inject constructor(
    private val networkRepository: NetworkRepository) {

    // Invoke function to get all crypto currencies from the network repository
    suspend operator fun invoke(): MutableLiveData<Resource<List<ExtendedCurrencyModel>>> {
        // Retrieve data from the network repository
        val data = networkRepository.getAllCryptos()

        // Create a list of ExtendedCurrencyModel objects based on the retrieved data
        val list = if (data != null) {
            listOf(
                ExtendedCurrencyModel(
                    "Bitcoin",
                    "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
                    data.bitcoin
                ),
                ExtendedCurrencyModel(
                    "Ethereum",
                    "https://assets.coingecko.com/coins/images/279/large/ethereum.png?1595348880",
                    data.ethereum
                ),
                ExtendedCurrencyModel(
                    "Ripple",
                    "https://assets.coingecko.com/coins/images/44/large/xrp-symbol-white-128.png?1605778731",
                    data.ripple
                )
            )
        } else {
            mutableListOf()
        }

        // Create a MutableLiveData object with the list as the success value
        return MutableLiveData(Resource.success(list))
    }
}
