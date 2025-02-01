package com.prashant.cleanarchitecture.domain.usecases.interfaces

import com.prashant.cleanarchitecture.data.model.Products
import com.prashant.cleanarchitecture.utils.NetworkResponse

interface GetProductsUseCase : UseCase<Unit, NetworkResponse<Products>> {
    override suspend fun invoke(param: Unit): NetworkResponse<Products>
}