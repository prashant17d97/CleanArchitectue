package com.prashant.cleanarchitecture.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.stopKoin

class KoinApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@KoinApplication)
            modules(appModule)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }

}