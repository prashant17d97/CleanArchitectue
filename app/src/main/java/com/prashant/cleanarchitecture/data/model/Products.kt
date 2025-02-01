package com.prashant.cleanarchitecture.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Products(
    @SerialName("products")
    val products: List<Product>,
    @SerialName("total")
    val total: Int, // 194
    @SerialName("skip")
    val skip: Int, // 0
    @SerialName("limit")
    val limit: Int, // 30
)