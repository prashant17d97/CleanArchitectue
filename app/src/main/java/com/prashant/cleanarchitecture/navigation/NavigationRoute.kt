package com.prashant.cleanarchitecture.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationRoute {

    @Serializable
    data object Home : NavigationRoute()

    @Serializable
    data class Detail(val id: String) : NavigationRoute()

}