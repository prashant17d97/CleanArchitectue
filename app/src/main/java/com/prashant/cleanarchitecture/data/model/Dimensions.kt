package com.prashant.cleanarchitecture.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Dimensions(
    @SerialName("depth")
    val depth: Double, // 28.01
    @SerialName("height")
    val height: Double, // 14.43
    @SerialName("width")
    val width: Double // 23.17
)