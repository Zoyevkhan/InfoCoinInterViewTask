package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {
    var products: MutableLiveData<UIState<List<Product>>> = MutableLiveData()
    var detailsProduct: MutableLiveData<UIState<Product>> = MutableLiveData()
    var homeRepo:HomeRepo = HomeRepo(RetrofitFactory.getRetrofit())
    lateinit var exceptionHandlerForProducts: CoroutineExceptionHandler
    lateinit var exceptionHandlerForDetails: CoroutineExceptionHandler

    init {
       inilizeExceptionHandlers()
        loadProducts()
    }


    private fun loadProducts() {
        products.value = UIState.isLoading()
        viewModelScope.launch(exceptionHandlerForProducts) {
            var response = homeRepo.getProductResponse()
            if (response.isSuccessful) {
                products.value = UIState.Success(response.body()?.products)
            } else {
                products.value = UIState.Eror(response.message())
            }
        }
    }

    public fun loadDetails(productId: Int) {
        detailsProduct.value = UIState.isLoading()
        viewModelScope.launch(exceptionHandlerForDetails) {
            var response =
                homeRepo.getDetailsProductResponse(productId)
            if (response.isSuccessful) {
                detailsProduct.value = UIState.Success(response.body())
            } else {
                detailsProduct.value = UIState.Eror(response.message())
            }
        }
    }
    fun inilizeExceptionHandlers(){
        exceptionHandlerForProducts = CoroutineExceptionHandler { _, exception ->
            products.value = UIState.Eror(exception.message)
        }
        exceptionHandlerForDetails = CoroutineExceptionHandler { _, exception ->
            detailsProduct.value = UIState.Eror(exception.message)
        }
    }


}