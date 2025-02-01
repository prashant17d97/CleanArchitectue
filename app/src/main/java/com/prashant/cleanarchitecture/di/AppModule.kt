package com.prashant.cleanarchitecture.di

import com.prashant.cleanarchitecture.di.modules.appStateModule
import com.prashant.cleanarchitecture.di.modules.networkModule
import com.prashant.cleanarchitecture.di.modules.repositoryModule
import com.prashant.cleanarchitecture.di.modules.useCaseModule
import com.prashant.cleanarchitecture.di.modules.viewModelModule
import org.koin.dsl.module

val appModule = module {
    includes(viewModelModule)
    includes(appStateModule)
    includes(networkModule)
    includes(repositoryModule)
    includes(useCaseModule)
}