package com.islamzada.cryptotrackerapp.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.islamzada.cryptotrackerapp.data.method.DeletePrice
import com.islamzada.cryptotrackerapp.data.method.InsertPrice
import com.islamzada.cryptotrackerapp.data.models.PriceModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MaxMinViewModel @Inject constructor (

    private val insertPrice: InsertPrice,
    private val deletePrice: DeletePrice,
): BaseViewModel() {
    // Function to insert a price into the data source
    fun insertPrice(priceModel: PriceModel) {
        viewModelScope.launch {
            insertPrice.invoke(priceModel)
        }
    }

    // Function to delete a price from the data source
    fun deletePrice(priceModel: PriceModel) {
        viewModelScope.launch {
            deletePrice.invoke(priceModel)
        }
    }
}
