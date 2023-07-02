package com.islamzada.cryptotrackerapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "price_table")

data class PriceModel (

    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val minPrice: Double,
    val maxPrice: Double,
    val currencyId: String,
    val currencyName: String,
    val currencyImage: String
)
