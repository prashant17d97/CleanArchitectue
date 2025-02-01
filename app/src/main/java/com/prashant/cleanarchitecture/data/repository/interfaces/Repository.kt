package com.prashant.cleanarchitecture.data.repository.interfaces

import com.prashant.cleanarchitecture.data.model.Product
import com.prashant.cleanarchitecture.data.model.Products
import com.prashant.cleanarchitecture.utils.NetworkResponse

interface Repository {
    suspend fun getProducts(): NetworkResponse<Products>
    suspend fun getSuperHeroById(id: String): NetworkResponse<Product>
}