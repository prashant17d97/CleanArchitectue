package com.prashant.cleanarchitecture.domain.usecases.impls

import com.prashant.cleanarchitecture.data.model.Product
import com.prashant.cleanarchitecture.data.repository.interfaces.Repository
import com.prashant.cleanarchitecture.domain.usecases.interfaces.GetHeroesByIdUseCase
import com.prashant.cleanarchitecture.utils.NetworkResponse

class GetHeroesByIdUseCaseImpl(private val repository: Repository) : GetHeroesByIdUseCase {
    override suspend fun invoke(param: String): NetworkResponse<Product> {
        return repository.getSuperHeroById(id =param)
    }
}