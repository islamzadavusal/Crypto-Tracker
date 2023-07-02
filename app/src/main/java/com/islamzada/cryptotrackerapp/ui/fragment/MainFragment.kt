package com.islamzada.cryptotrackerapp.ui.fragment

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.islamzada.cryptotrackerapp.R
import com.islamzada.cryptotrackerapp.databinding.FragmentMainBinding
import com.islamzada.cryptotrackerapp.ui.adapter.CurrencyAdapter
import com.islamzada.cryptotrackerapp.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: CurrencyAdapter

    // Create the fragment's view
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        //Network
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo == null || !networkInfo.isConnectedOrConnecting) {
            Toast.makeText(context, "You do not have an internet connection.", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.cryptoLiveData
        }

        return binding.root
    }

    // Initialize the fragment's views and data
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create and set up the adapter for the RecyclerView
        adapter = CurrencyAdapter(requireContext(), emptyList())
        binding.rvCrypto.adapter = adapter
        binding.rvCrypto.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        // Set item click listener for the adapter
        adapter.onItemClick = {
            val bottomSheet = SetMinMaxRateFragment(currencyModel = it)
            bottomSheet.show(childFragmentManager, "BottomSheetDialogFragment")
        }

        // Set text change listener for the search EditText
        binding.editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Filter the adapter's data based on the search query
                adapter.filterByTitle(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // Observe the live data from the view model
        viewModel.cryptoLiveData.observe(viewLifecycleOwner) { currencies ->
            adapter.originalList = currencies
            adapter.filterByTitle(binding.editTextSearch.text.toString())
        }
    }
}
