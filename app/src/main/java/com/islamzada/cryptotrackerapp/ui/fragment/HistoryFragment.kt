package com.islamzada.cryptotrackerapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.islamzada.cryptotrackerapp.databinding.FragmentHistoryBinding
import com.islamzada.cryptotrackerapp.ui.adapter.HistoryAdapter
import com.islamzada.cryptotrackerapp.ui.viewmodel.HistoryViewModel
import com.islamzada.cryptotrackerapp.ui.viewmodel.MaxMinViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var adapter: HistoryAdapter
    private val historyViewModel: HistoryViewModel by viewModels()
    private val maxMinViewModel: MaxMinViewModel by viewModels()

    // Create the fragment's view
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Initialize the fragment's views and data
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data: HistoryFragmentArgs by navArgs()
        val currencyId = data.currencyId

        // Retrieve history data by currencyId
        historyViewModel.getAllHistoriesByCurrencyId(currencyId)

        // Observe the filtered history data
        historyViewModel.previousHistoriesFilteredLiveData.observe(viewLifecycleOwner) { previousHistoriesList ->
            adapter = HistoryAdapter(requireContext(), previousHistoriesList)
            binding.rvHistory.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.rvHistory.adapter = adapter
        }

        // Attach swipe callback to RecyclerView
        ItemTouchHelper(swipeCallBack).attachToRecyclerView(binding.rvHistory)
    }

    // Swipe callback for RecyclerView items
    private val swipeCallBack = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.layoutPosition
            val priceHistory = adapter.list[position]
            maxMinViewModel.deletePrice(priceHistory)
        }
    }
}
