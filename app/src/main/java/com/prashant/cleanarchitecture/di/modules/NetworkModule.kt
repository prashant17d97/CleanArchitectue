package com.prashant.cleanarchitecture.di.modules

import com.prashant.cleanarchitecture.data.remote.ApiInstance
import com.prashant.cleanarchitecture.data.remote.apis.ProductApis
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {
    // Define the BASE_URL as a singleton string value.
    single<String>(named("BASE_URL")) {
        ApiInstance.BASE_URL
    }

    // Provide ApiInstance as a singleton, since it's managing Retrofit.
    single {
        ApiInstance()
    }

    // Provide APIs interface using the ApiInstance's createService method.
    single<ProductApis> {
        val apiInstance: ApiInstance = get()  // Get ApiInstance from Koin
        apiInstance.createService(ProductApis::class.java)  // Create and return the APIs service
    }
}
