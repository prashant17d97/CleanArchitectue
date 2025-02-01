package com.prashant.cleanarchitecture.di.modules

import com.prashant.cleanarchitecture.utils.appstate.AppStatesOwner
import com.prashant.cleanarchitecture.utils.appstate.AppStatesOwnerImpl
import org.koin.dsl.module

val appStateModule = module {
    single<AppStatesOwner> { AppStatesOwnerImpl() }
}