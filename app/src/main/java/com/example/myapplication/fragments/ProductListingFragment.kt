package com.example.myapplication.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.databinding.FragmentProductListingBinding
import com.example.myapplication.HomeViewModel
import com.example.myapplication.ListingListener
import com.example.myapplication.MainActivity

class ProductListingFragment() : Fragment(){
    private var _binding: FragmentProductListingBinding?=null
    private val binding get()=_binding!!
    lateinit var callback:ListingListener
    val homeviewmodel: HomeViewModel by activityViewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentProductListingBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeviewmodel=homeviewmodel
        binding.callback=callback
        binding.lifecycleOwner=viewLifecycleOwner
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback=context as MainActivity
    }
}