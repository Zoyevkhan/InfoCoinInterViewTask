package com.example.myapplication

data class ProductResponse(
    var products: ArrayList<Product>,
    var total: Int,
    var skip: Int,
    var limit: Int
)
