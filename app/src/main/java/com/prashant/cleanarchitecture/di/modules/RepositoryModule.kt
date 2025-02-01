package com.prashant.cleanarchitecture.di.modules

import com.prashant.cleanarchitecture.data.repository.impls.RepositoryImpl
import com.prashant.cleanarchitecture.data.repository.interfaces.Repository
import org.koin.dsl.module

val repositoryModule = module {
    single<Repository> { RepositoryImpl(productApis = get()) }
}