package com.islamzada.cryptotrackerapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.islamzada.cryptotrackerapp.data.method.GetAllHistories
import com.islamzada.cryptotrackerapp.data.method.GetAllHistoriesByCurrencyId
import com.islamzada.cryptotrackerapp.data.models.PriceModel
import com.islamzada.cryptotrackerapp.message.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor (

    private val getAllPreviousHistories: GetAllHistories,
    private val getAllPreviousHistoriesFilteredByCurrencyIdUseCase: GetAllHistoriesByCurrencyId
) : BaseViewModel() {
    private val _previousHistoriesLiveData = MutableLiveData<List<PriceModel>>()
    val previousHistoriesLiveData get() = _previousHistoriesLiveData

    private val _previousHistoriesFilteredLiveData = MutableLiveData<List<PriceModel>>()
    val previousHistoriesFilteredLiveData get() = _previousHistoriesFilteredLiveData

    // LiveData to represent whether loading previous histories is in progress
    val isLoadingPreviousHistories = MutableLiveData<Boolean>(false)

    // LiveData to hold error messages in case of errors during history retrieval
    val errorMessagePreviousHistories = MutableLiveData<String>()

    // Initialize the ViewModel
    init {
        // Fetch all histories when the ViewModel is created
        getAllHistories()
    }

    // Fetch all histories from the data source
    private fun getAllHistories() {
        // Set isLoadingPreviousHistories to true to indicate loading is in progress
        isLoadingPreviousHistories.value = true
        viewModelScope.launch {
            val result = getAllPreviousHistories.invoke()

            // Handle the result of the history retrieval
            when (result.status) {
                Status.SUCCESS -> {
                    // If the retrieval is successful and there is data available, update the live data
                    if (result.data != null) {
                        _previousHistoriesLiveData.value = result.data!!
                        isLoadingPreviousHistories.value = result.data.isEmpty()
                    }
                }

                Status.ERROR -> {
                    // If there is an error during the retrieval, update the error message and stop loading
                    errorMessagePreviousHistories.value = result.message!!
                    isLoadingPreviousHistories.value = false
                }

                Status.LOADING -> {
                    // Indicate that loading is still in progress
                    isLoadingPreviousHistories.value = true
                }
            }
        }
    }

    // Fetch histories filtered by currencyId from the data source
    fun getAllHistoriesByCurrencyId(currencyId: String) {
        // Set isLoadingPreviousHistories to true to indicate loading is in progress
        isLoadingPreviousHistories.value = true
        viewModelScope.launch {
            val result = getAllPreviousHistoriesFilteredByCurrencyIdUseCase.invoke(currencyId)

            // Handle the result of the filtered history retrieval
            when (result.status) {
                Status.SUCCESS -> {
                    // If the retrieval is successful and there is data available, update the live data
                    if (result.data != null) {
                        _previousHistoriesFilteredLiveData.value = result.data!!
                        isLoadingPreviousHistories.value = result.data.isEmpty()
                    }
                }

                Status.ERROR -> {
                    // If there is an error during the retrieval, update the error message and stop loading
                    errorMessagePreviousHistories.value = result.message!!
                    isLoadingPreviousHistories.value = false
                    println(result.message)
                }

                Status.LOADING -> {
                    // Indicate that loading is still in progress
                    isLoadingPreviousHistories.value = true
                }
            }
        }
    }
}
