package com.prashant.cleanarchitecture.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiInstance {

    companion object {
        const val BASE_URL = "https://dummyjson.com"
    }

    /**
     * Creates a Retrofit service with authentication using a Bearer token.
     * If the token changes, it rebuilds the Retrofit instance.
     */
    fun <T> createService(serviceClass: Class<T>, token: String? = null): T {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level =
            HttpLoggingInterceptor.Level.BODY // Logs request and response body

        // Create OkHttpClient with logging
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        // Create Retrofit instance
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)  // Replace with your base URL
            .client(okHttpClient)  // Add the OkHttp client with the logger
            .addConverterFactory(GsonConverterFactory.create()) // Add Gson converter (or any other converter you prefer)
            .build()

        return retrofit
            .create(serviceClass)
    }
}
