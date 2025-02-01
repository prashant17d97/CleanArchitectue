package com.prashant.cleanarchitecture.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    @SerialName("availabilityStatus")
    val availabilityStatus: String, // Low Stock
    @SerialName("brand")
    val brand: String, // Essence
    @SerialName("category")
    val category: String, // beauty
    @SerialName("description")
    val description: String, // The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula.
    @SerialName("dimensions")
    val dimensions: Dimensions,
    @SerialName("discountPercentage")
    val discountPercentage: Double, // 7.17
    @SerialName("id")
    val id: Int, // 1
    @SerialName("images")
    val images: List<String>,
    @SerialName("meta")
    val meta: Meta,
    @SerialName("minimumOrderQuantity")
    val minimumOrderQuantity: Int, // 24
    @SerialName("price")
    val price: Double, // 9.99
    @SerialName("rating")
    val rating: Double, // 4.94
    @SerialName("returnPolicy")
    val returnPolicy: String, // 30 days return policy
    @SerialName("reviews")
    val reviews: List<Review>,
    @SerialName("shippingInformation")
    val shippingInformation: String, // Ships in 1 month
    @SerialName("sku")
    val sku: String, // RCH45Q1A
    @SerialName("stock")
    val stock: Int, // 5
    @SerialName("tags")
    val tags: List<String>,
    @SerialName("thumbnail")
    val thumbnail: String, // https://cdn.dummyjson.com/products/images/beauty/Essence%20Mascara%20Lash%20Princess/thumbnail.png
    @SerialName("title")
    val title: String, // Essence Mascara Lash Princess
    @SerialName("warrantyInformation")
    val warrantyInformation: String, // 1 month warranty
    @SerialName("weight")
    val weight: Int // 2
)

val product = Product(
    availabilityStatus = "volutpat",
    brand = "commodo",
    category = "pri",
    description = "affert",
    dimensions = Dimensions(
        depth = 12.13,
        height = 14.15,
        width = 16.17
    ),
    discountPercentage = 18.19,
    id = 8820,
    images = listOf(),
    meta = Meta(
        barcode = "discere",
        createdAt = "debet",
        qrCode = "quo",
        updatedAt = "in"
    ),
    minimumOrderQuantity = 8103,
    price = 20.21,
    rating = 22.23,
    returnPolicy = "his",
    reviews = listOf(),
    shippingInformation = "sea",
    sku = "suspendisse",
    stock = 3279,
    tags = listOf(),
    thumbnail = "quaestio",
    title = "neque",
    warrantyInformation = "taciti",
    weight = 3160
)
