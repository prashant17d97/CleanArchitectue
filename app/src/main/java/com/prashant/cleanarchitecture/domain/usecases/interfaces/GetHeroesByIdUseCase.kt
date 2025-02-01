package com.prashant.cleanarchitecture.domain.usecases.interfaces

import com.prashant.cleanarchitecture.data.model.Product
import com.prashant.cleanarchitecture.utils.NetworkResponse

interface GetHeroesByIdUseCase : UseCase<String, NetworkResponse<Product>> {
    override suspend fun invoke(param: String): NetworkResponse<Product>
}