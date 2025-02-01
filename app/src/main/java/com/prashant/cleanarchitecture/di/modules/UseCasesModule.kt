package com.prashant.cleanarchitecture.di.modules

import com.prashant.cleanarchitecture.domain.usecases.impls.GetHeroesByIdUseCaseImpl
import com.prashant.cleanarchitecture.domain.usecases.impls.GetProductsUseCaseImpl
import com.prashant.cleanarchitecture.domain.usecases.interfaces.GetHeroesByIdUseCase
import com.prashant.cleanarchitecture.domain.usecases.interfaces.GetProductsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single<GetProductsUseCase> { GetProductsUseCaseImpl(repository = get()) }
    single<GetHeroesByIdUseCase> { GetHeroesByIdUseCaseImpl(repository = get()) }
}