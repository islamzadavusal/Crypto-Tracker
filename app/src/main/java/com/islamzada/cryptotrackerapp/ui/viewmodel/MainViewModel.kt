package com.islamzada.cryptotrackerapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.islamzada.cryptotrackerapp.data.method.GetAllCryptoCurrencies
import com.islamzada.cryptotrackerapp.data.models.ExtendedCurrencyModel
import com.islamzada.cryptotrackerapp.message.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (

    private val getAllCryptoCurrencies: GetAllCryptoCurrencies
) : BaseViewModel() {
    // LiveData to hold the list of cryptocurrencies
    private var _cryptoLiveData = MutableLiveData<List<ExtendedCurrencyModel>>()
    val cryptoLiveData get() = _cryptoLiveData

    // Initialize the ViewModel
    init {
        // Fetch all cryptocurrencies when the ViewModel is created
        getAll()
    }

    // Fetch all cryptocurrencies from the data source
    private fun getAll() {
        viewModelScope.launch {
            val live = getAllCryptoCurrencies.invoke()

            // Check the status of the result
            if (live.value?.status == Status.SUCCESS) {
                // If the retrieval is successful and there is data available, update the live data
                _cryptoLiveData.value = live.value!!.data!!
            }
        }
    }
}
