package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.fragments.DetailsFragment
import com.example.myapplication.fragments.ProductListingFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log

class MainActivity : AppCompatActivity(),ListingListener {
    lateinit var binding:ActivityMainBinding
    val homeViewModel: HomeViewModel by viewModels<HomeViewModel>()
    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        if(savedInstanceState==null)
        addFragment(ProductListingFragment())

    }
    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(binding.homeContainer.id, fragment)
            .addToBackStack("ProductListingFragment")
            .commit()
    }

    override fun onItemClicked(product: Product) {
        homeViewModel.loadDetails(product.id)
        replaceFragment(DetailsFragment())
    }

    override fun onDetailsItemClicked(product: Product) {
        // here we are getting the add button callback with the product
      Toast.makeText(this,"${product.title} is added",Toast.LENGTH_LONG).show()
    }

    private fun replaceFragment(
        fragment: Fragment
    ) {
         supportFragmentManager.beginTransaction()
            .replace(binding.homeContainer.id, fragment)
             .addToBackStack("Details")
            .commit()
    }

    override fun onBackPressed() {
        var fragment = supportFragmentManager.findFragmentById(binding.homeContainer.id)
        if (fragment is DetailsFragment) {
            supportFragmentManager.popBackStack()
        } else if(fragment is ProductListingFragment){
            finish()
        }else {
            super.onBackPressed()
        }
    }
}