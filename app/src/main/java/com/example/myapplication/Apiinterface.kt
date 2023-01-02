package com.example.myapplication

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Apiinterface {


    @GET("/products")
    suspend fun getProductResponse():Response<ProductResponse>

    @GET("/products/{id}")
    suspend fun getProductResponse(
        @Path("id") id:Int
    ):Response<Product>
}