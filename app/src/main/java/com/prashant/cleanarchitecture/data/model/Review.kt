package com.prashant.cleanarchitecture.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Review(
    @SerialName("comment")
    val comment: String, // Very unhappy with my purchase!
    @SerialName("date")
    val date: String, // 2024-05-23T08:56:21.618Z
    @SerialName("rating")
    val rating: Int, // 2
    @SerialName("reviewerEmail")
    val reviewerEmail: String, // john.doe@x.dummyjson.com
    @SerialName("reviewerName")
    val reviewerName: String // John Doe
)