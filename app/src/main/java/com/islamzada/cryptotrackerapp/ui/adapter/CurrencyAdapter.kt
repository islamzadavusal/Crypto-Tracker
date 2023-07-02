package com.islamzada.cryptotrackerapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.islamzada.cryptotrackerapp.data.models.ExtendedCurrencyModel
import com.islamzada.cryptotrackerapp.databinding.RvItemBinding

class CurrencyAdapter(val context: Context, var originalList: List<ExtendedCurrencyModel>) :
    RecyclerView.Adapter<CurrencyAdapter.CurrencyHolder>() {

    private var filteredList: List<ExtendedCurrencyModel> = originalList

    // Callback for item click event
    var onItemClick: (ExtendedCurrencyModel) -> Unit = {}

    inner class CurrencyHolder(val binding: RvItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    // Create view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        val binding = RvItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return CurrencyHolder(binding)
    }

    // Get item count
    override fun getItemCount(): Int = filteredList.size

    // Bind data to views
    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
        val view = holder.binding
        val exCurrencyModel = filteredList[position]

        val price = "%.2f".format(exCurrencyModel.currencyModel.usd)
        val totalVolume = "%.2f".format(exCurrencyModel.currencyModel.usd_24h_vol)
        val priceChange = "%.2f".format(exCurrencyModel.currencyModel.usd_24h_change)

        // Load image using Glide library
        Glide.with(context).load(exCurrencyModel.image).into(view.imageViewIcon)

        view.textViewName.text = exCurrencyModel.title
        view.textViewPrice.text = "Price: $price $ "
        view.textViewTotalVolume.text = "Total volume: $totalVolume $ "
        view.textViewPriceChange.text = "Price change: $priceChange $ "

        // Set click listener for the item
        holder.itemView.setOnClickListener {
            onItemClick.invoke(exCurrencyModel)
        }
    }

    // Filter items by title
    fun filterByTitle(title: String) {
        filteredList = if (title.isEmpty()) {
            originalList
        } else {
            originalList.filter { it.title.contains(title, true) }
        }
        notifyDataSetChanged()
    }
}