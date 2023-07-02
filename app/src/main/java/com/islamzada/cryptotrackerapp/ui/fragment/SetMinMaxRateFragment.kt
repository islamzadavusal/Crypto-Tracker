package com.islamzada.cryptotrackerapp.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.islamzada.cryptotrackerapp.R
import com.islamzada.cryptotrackerapp.data.models.ExtendedCurrencyModel
import com.islamzada.cryptotrackerapp.data.models.PriceModel
import com.islamzada.cryptotrackerapp.databinding.FragmentSetMinMaxRateBinding
import com.islamzada.cryptotrackerapp.ui.viewmodel.MaxMinViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetMinMaxRateFragment(val currencyModel: ExtendedCurrencyModel) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentSetMinMaxRateBinding
    private val viewModel: MaxMinViewModel by viewModels()

    // Create the fragment's view
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSetMinMaxRateBinding.inflate(inflater, container, false)

        return binding.root
    }

    // Initialize the fragment's views and data
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the title of the fragment
        binding.titleText.text = "Set price for ${currencyModel.title} "

        // Handle the confirm button click event
        binding.confirmBtn.setOnClickListener {
            val maxPriceText = binding.setPriceMax.text.toString()
            val minPriceText = binding.setPriceMin.text.toString()

            if (maxPriceText.isNotEmpty() && minPriceText.isNotEmpty()) {
                val maxPrice = maxPriceText.toDouble()
                val minPrice = minPriceText.toDouble()

                // Insert the price data into the view model
                viewModel.insertPrice(
                    PriceModel(
                        id = 0,
                        minPrice = minPrice,
                        maxPrice = maxPrice,
                        currencyId = currencyModel.title.lowercase(),
                        currencyName = currencyModel.title,
                        currencyImage = ""
                    )
                )

                // Navigate back to the MainFragment
                findNavController().navigate(R.id.mainFragment)
            } else {
                Toast.makeText(requireContext(), "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            }
        }

        // Handle the check button click event
        binding.checkBtn.setOnClickListener {
            // Navigate to the HistoryFragment with the selected currency ID as a parameter
            val action = MainFragmentDirections.actionMainFragmentToHistoryFragment(currencyModel.title.lowercase())
            findNavController().navigate(action)
        }
    }
}