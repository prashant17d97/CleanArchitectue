package com.prashant.cleanarchitecture.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Meta(
    @SerialName("barcode")
    val barcode: String, // 9164035109868
    @SerialName("createdAt")
    val createdAt: String, // 2024-05-23T08:56:21.618Z
    @SerialName("qrCode")
    val qrCode: String, // https://assets.dummyjson.com/public/qr-code.png
    @SerialName("updatedAt")
    val updatedAt: String // 2024-05-23T08:56:21.618Z
)