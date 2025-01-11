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
fun <R : Any> Nav(
    viewModel: NavViewModel<R>,
    destinationRegistrar: DestinationRegistrar,
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
        destinationRegistrar = destinationRegistrar,
    )
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun NavHost(
    navController: NavHostController,
    startDestination: Any,
    destinationRegistrar: DestinationRegistrar,
) {
    SharedTransitionLayout {
        CompositionLocalProvider(
            LocalSharedTransitionScope provides this,
            LocalBackNavigator provides BackNavigatorImpl(navController),
        ) {
            NavHost(navController = navController, startDestination = startDestination) {
                with(destinationRegistrar) {
                    registerDestinations()
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
val LocalSharedTransitionScope = staticCompositionLocalOf<SharedTransitionScope> {
    error("CompositionLocal SharedTransitionScope not present")
}

val LocalBackNavigator = staticCompositionLocalOf<BackNavigator> {
    error("CompositionLocal BackNavigator not present")
}

