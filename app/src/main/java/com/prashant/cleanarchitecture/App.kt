package com.prashant.cleanarchitecture

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.exitUntilCollapsedScrollBehavior
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.prashant.cleanarchitecture.domain.model.BottomBarItem
import com.prashant.cleanarchitecture.navigation.NavigationGraph
import com.prashant.cleanarchitecture.navigation.NavigationRoute
import com.prashant.cleanarchitecture.ui.commoncomponents.AlertDialog
import com.prashant.cleanarchitecture.ui.commoncomponents.ProgressLoader
import com.prashant.cleanarchitecture.ui.theme.CleanArchitectureTheme
import com.prashant.cleanarchitecture.utils.UICommnetUtils.color
import com.prashant.cleanarchitecture.utils.appstate.AlertType
import com.prashant.cleanarchitecture.utils.appstate.AlertType.SnackBar
import com.prashant.cleanarchitecture.utils.appstate.UIAlertModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel,
    navController: NavHostController = rememberNavController()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val alerts by mainViewModel.alertEvent.collectAsState(initial = UIAlertModel())
    val currentDestination by navController.currentBackStackEntryFlow.collectAsState(null)
    var bottomBarItems by remember {
        mutableStateOf(
            listOf(
                BottomBarItem(
                    icon = Icons.Filled.Home,
                    label = "Home",
                    selected = true,
                    route = NavigationRoute.Home
                ),
                BottomBarItem(
                    icon = Icons.Filled.Edit,
                    label = "Detail",
                    selected = false,
                    route = NavigationRoute.Detail(id = "1")
                )
            )
        )
    }

    val snackBarType: SnackBar by remember {
        mutableStateOf(SnackBar())
    }
    val backNavigation: @Composable () -> Unit = {
        if (currentDestination?.destination?.route != NavigationRoute.Home::class.java.canonicalName) {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                    contentDescription = Icons.AutoMirrored.Rounded.KeyboardArrowLeft.name
                )
            }

        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) {
                Snackbar(
                    snackbarData = it,
                    containerColor = snackBarType.snackBarColor.color(),
                    contentColor = contentColorFor(snackBarType.snackBarColor.color()),
                )
            }
        },
        topBar = {
            Surface(shadowElevation = 4.dp) {
                TopAppBar(
                    navigationIcon = backNavigation,
                    title = {
                        Text("E Commerce")
                    },
                    scrollBehavior = exitUntilCollapsedScrollBehavior()
                )
            }
        },
        bottomBar = {
            BottomBarContainer(bottomBarItems = bottomBarItems) {
                bottomBarItems= bottomBarItems.map { item ->
                    item.copy(selected = it.route == item.route)
                }
                navController.navigate(it.route)
            }
        }
    ) {
        NavigationGraph(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp),
            navController = navController
        )

        when (val alertType = alerts.alertType) {
            is AlertType.AlertDialog -> AlertDialog(
                alertDialog = alertType,
                dismissAlert = mainViewModel::dismissAlert
            )

            is AlertType.ProgressLoader -> ProgressLoader(
                alertType = alertType,
                showProgress = alerts.show,
                dismissAlert = mainViewModel::dismissAlert
            )

            is SnackBar -> {
                ShowSnackBar(
                    alertType,
                    snackbarHostState,
                    dismissAlert = mainViewModel::dismissAlert
                )
            }

            is AlertType.Toast -> Toast.makeText(
                LocalContext.current,
                alertType.message,
                Toast.LENGTH_SHORT
            ).show()

            AlertType.Nothing -> {
                /* Nothing to do*/
            }

        }
    }
}

@Composable
private fun ShowSnackBar(
    alertType: SnackBar,
    snackbarHostState: SnackbarHostState,
    dismissAlert: () -> Unit
) {
    LaunchedEffect(Unit) {
        val snackbarResult = snackbarHostState.showSnackbar(
            message = alertType.message,
            actionLabel = alertType.actionLabel,
            duration = alertType.duration,
            withDismissAction = alertType.withDismissAction
        )

        when (snackbarResult) {
            SnackbarResult.ActionPerformed -> {
                dismissAlert()
                alertType.action?.invoke()
            }

            SnackbarResult.Dismissed -> {
                dismissAlert()
                alertType.onDismiss?.invoke()
            }
        }
    }
}


@Composable
fun BottomBarContainer(
    modifier: Modifier = Modifier,
    bottomBarItems: List<BottomBarItem>, onClick: (BottomBarItem) -> Unit
) {
    NavigationBar(
        modifier = modifier.clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)),
        tonalElevation = 8.dp
    ) {
        bottomBarItems.forEach { item ->
            NavigationBarItem(
                selected = item.selected,
                onClick = { onClick(item) },
                icon = { Icon(imageVector = item.icon, contentDescription = item.icon.name) },
                label = { Text(item.label) }
            )
        }
    }
}


@Preview
@Composable
private fun BBPreview() {
    CleanArchitectureTheme {
//        BottomBarContainer()
    }
}