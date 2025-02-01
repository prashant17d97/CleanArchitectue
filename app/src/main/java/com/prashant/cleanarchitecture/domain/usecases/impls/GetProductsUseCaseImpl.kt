package com.prashant.cleanarchitecture.domain.usecases.impls

import com.prashant.cleanarchitecture.data.model.Products
import com.prashant.cleanarchitecture.data.repository.interfaces.Repository
import com.prashant.cleanarchitecture.domain.usecases.interfaces.GetProductsUseCase
import com.prashant.cleanarchitecture.utils.NetworkResponse

class GetProductsUseCaseImpl(private val repository: Repository) : GetProductsUseCase {
    override suspend fun invoke(param: Unit): NetworkResponse<Products> =
        repository.getProducts()
}