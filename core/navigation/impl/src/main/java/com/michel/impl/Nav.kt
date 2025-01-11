package com.michel.impl

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.michel.api.BackNavigator

@Composable
fun <ROUTE : Any> Nav(
    viewModel: NavViewModel<ROUTE>,
    registrar: Registrar,
) {
    val navController = rememberNavController()
    LaunchedEffect(viewModel) {
        viewModel.navMessages.collect { action ->
            when (action) {
                is NavMessage.Navigate -> navController.navigate(route = action.route)
                is NavMessage.Up -> navController.navigateUp()
            }
        }
    }
    NavHost(
        navController = navController,
        startDestination = viewModel.startDestinationRoute,
        registrar = registrar,
    )
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun NavHost(
    navController: NavHostController,
    startDestination: Any,
    registrar: Registrar,
) {
    SharedTransitionLayout {
        CompositionLocalProvider(
            LocalSharedTransitionScope provides this,
            LocalBackNavigator provides BackNavigatorImpl(navController),
        ) {
            NavHost(navController = navController, startDestination = startDestination) {
                with(registrar) {
                    registerDestinations()
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
val LocalSharedTransitionScope =
    staticCompositionLocalOf<SharedTransitionScope> {
        error("CompositionLocal LocalSharedTransition not present")
    }

val LocalBackNavigator =
    staticCompositionLocalOf<BackNavigator> {
        error("CompositionLocal NavHostController not present")
    }

