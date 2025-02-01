package com.prashant.cleanarchitecture.di.modules

import com.prashant.cleanarchitecture.MainViewModel
import com.prashant.cleanarchitecture.ui.screens.detail.DetailViewModel
import com.prashant.cleanarchitecture.ui.screens.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(
            appStatesOwner = get()
        )
    }

    viewModel {
        HomeViewModel(
            appStatesOwner = get(),
            getProductsUseCase = get()
        )
    }

    viewModel {
        DetailViewModel(
            appStatesOwner = get(),
            getHeroesByIdUseCase = get()
        )
    }
}