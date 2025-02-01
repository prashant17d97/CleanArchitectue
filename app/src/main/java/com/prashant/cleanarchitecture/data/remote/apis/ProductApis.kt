package com.prashant.cleanarchitecture.data.remote.apis

import com.prashant.cleanarchitecture.data.model.Product
import com.prashant.cleanarchitecture.data.model.Products
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApis {
    @GET("/products")
    suspend fun getProducts(): Response<Products>

    @GET("/products/{id}")
    suspend fun getProductById(@Path("id") id: String): Response<Product>

}