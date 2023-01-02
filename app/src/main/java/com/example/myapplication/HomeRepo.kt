package com.example.myapplication

class HomeRepo(val webservice:Apiinterface) {
    suspend fun getProductResponse() =webservice.getProductResponse()
    suspend fun getDetailsProductResponse(productId:Int) =webservice.getProductResponse(productId)
}