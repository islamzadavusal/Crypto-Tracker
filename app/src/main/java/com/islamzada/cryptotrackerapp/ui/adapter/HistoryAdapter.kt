package com.islamzada.cryptotrackerapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.islamzada.cryptotrackerapp.data.models.PriceModel
import com.islamzada.cryptotrackerapp.databinding.RvHistoryBinding

class HistoryAdapter(val context: Context, val list: List<PriceModel>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryHolder>() {

    inner class HistoryHolder(val binding: RvHistoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    // Create view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        val binding = RvHistoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return HistoryHolder(binding)
    }

    // Get item count
    override fun getItemCount(): Int = list.size

    // Bind data to views
    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        val view = holder.binding
        val historyModel = list[position]

        // Set max price text
        view.textViewMax.text = "Max: ${historyModel.maxPrice} $ "

        // Set min price text
        view.textViewMin.text = "Min: ${historyModel.minPrice} $ "

        // Glide.with(context).load(historyModel.currencyImage).into(view.imageViewIcon)
    }
}
