package com.prashant.cleanarchitecture.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.prashant.cleanarchitecture.ui.screens.detail.DetailScreenView
import com.prashant.cleanarchitecture.ui.screens.detail.DetailViewModel
import com.prashant.cleanarchitecture.ui.screens.home.Home
import com.prashant.cleanarchitecture.ui.screens.home.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    val homeViewModel: HomeViewModel = koinViewModel()
    val detailViewModel: DetailViewModel = koinViewModel()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavigationRoute.Home
    ) {
        composable<NavigationRoute.Home> {
            Home(
                homeViewModel = homeViewModel
            ) {
                navController.navigate(NavigationRoute.Detail(it))
            }
        }

        composable<NavigationRoute.Detail> {
            val id = it.toRoute<NavigationRoute.Detail>().id
            DetailScreenView(
                id = id,
                detailViewModel = detailViewModel
            )
        }
    }
}