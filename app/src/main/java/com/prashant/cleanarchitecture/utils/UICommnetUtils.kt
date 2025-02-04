package com.prashant.cleanarchitecture.utils

import android.content.Context
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import com.prashant.cleanarchitecture.navigation.NavigationRoute
import com.prashant.cleanarchitecture.utils.appstate.SnackBarColor

object UICommnetUtils {

    @Composable
    fun SnackBarColor.color(): Color {
        val colorScheme = MaterialTheme.colorScheme
        return when (this) {
            SnackBarColor.Info -> colorScheme.primary
            SnackBarColor.Success -> colorScheme.secondary
            SnackBarColor.Warning -> colorScheme.tertiary
            SnackBarColor.Error -> colorScheme.onError
        }
    }

    fun Int.string(context: Context): String {
        return context.getString(this)
    }

    @Composable
    fun Int.string(): String {
        return stringResource(this)
    }

    fun NavBackStackEntry?.shouldShowBottomBar(): Boolean {
        return when(this?.destination?.route){
            NavigationRoute.Home::class.java.canonicalName ->  true
            NavigationRoute.Detail::class.java.canonicalName ->  true
            else -> return false
        }
    }
}