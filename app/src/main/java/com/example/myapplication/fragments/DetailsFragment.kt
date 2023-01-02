package com.example.myapplication.fragments

import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.myapplication.HomeViewModel
import com.example.myapplication.ListingListener
import com.example.myapplication.MainActivity
import com.example.myapplication.UIState
import com.example.myapplication.databinding.DetailsFragmentBinding
import com.example.myapplication.databinding.FragmentProductListingBinding

class DetailsFragment  : Fragment() {
    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!
    lateinit var callback: ListingListener
    val homeviewmodel: HomeViewModel by activityViewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.callback = callback
        registerObserver()
    }

    private fun registerObserver() {
        homeviewmodel.detailsProduct.observe(viewLifecycleOwner, Observer { UiState ->
            when (UiState) {
                is UIState.isLoading -> {
                    with(binding) {
                        detailsContainer.visibility = View.GONE
                        progressbar.visibility = View.VISIBLE
                    }
                }
                is UIState.Success -> {
                    with(binding) {
                        detailsContainer.visibility = View.VISIBLE
                        progressbar.visibility = View.GONE
                        product = UiState.data
                    }

                }
                is UIState.Eror -> {
                    with(binding) {
                        detailsContainer.visibility = View.GONE
                        progressbar.visibility = View.GONE
                    }
                    Toast.makeText(activity, UiState.message, Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as MainActivity
    }
}