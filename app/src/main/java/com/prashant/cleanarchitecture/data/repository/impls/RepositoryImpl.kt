package com.prashant.cleanarchitecture.data.repository.impls

import com.prashant.cleanarchitecture.data.model.Product
import com.prashant.cleanarchitecture.data.model.Products
import com.prashant.cleanarchitecture.data.remote.apis.ProductApis
import com.prashant.cleanarchitecture.data.repository.interfaces.Repository
import com.prashant.cleanarchitecture.utils.NetworkResponse
import com.prashant.cleanarchitecture.utils.NetworkUtils.toNetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryImpl(private val productApis: ProductApis) : Repository {

    override suspend fun getProducts(): NetworkResponse<Products> {
        return withContext(Dispatchers.IO) {
            productApis.getProducts().toNetworkResponse()
        }
    }

    override suspend fun getSuperHeroById(id: String): NetworkResponse<Product> {
        return withContext(Dispatchers.IO) {
            productApis.getProductById(id).toNetworkResponse()
        }
    }
}