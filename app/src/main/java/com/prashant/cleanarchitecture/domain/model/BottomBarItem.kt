package com.prashant.cleanarchitecture.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.prashant.cleanarchitecture.navigation.NavigationRoute

data class BottomBarItem(
    val icon: ImageVector = Icons.Filled.Home,//ImageVector.Builder("Home", 24.dp, 24.dp, 0f, 0f).build(),
    val label: String = "Home",
    val selected: Boolean = false,
    val route: NavigationRoute,
)