package com.islamzada.cryptotrackerapp.data.method

import com.islamzada.cryptotrackerapp.data.models.PriceModel
import com.islamzada.cryptotrackerapp.data.repo.HistoryRepository
import com.islamzada.cryptotrackerapp.message.Resource
import javax.inject.Inject

class GetAllHistoriesByCurrencyId @Inject constructor(
    private val priceRepository: HistoryRepository, ) {

    // Invoke function to get all histories filtered by currency ID from the price repository
    suspend operator fun invoke(currencyId: String): Resource<List<PriceModel>> {
        // Retrieve data from the price repository based on the provided currency ID
        val data = priceRepository.getAllHistoriesByCurrencyId(currencyId)

        // Check if the retrieved data is not null
        return if (data != null) {
            // Return the retrieved data as a success resource
            Resource.success(data)
        } else {
            // Return an error resource with a custom error message
            Resource.error("ERROR", null)
        }
    }

}
