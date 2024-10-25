package com.michel.weatherit.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.michel.profile.navigation.profileScreen
import com.michel.weather.navigation.WeatherRoute
import com.michel.weather.navigation.weatherScreen
import com.michel.weatherit.ui.WiAppState

@Composable
fun WiAppNavHost(
    modifier: Modifier = Modifier,
    appState: WiAppState,
    onShowSnackbar: suspend (String, String) -> Boolean,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = WeatherRoute,
        modifier = modifier,
    ) {
        weatherScreen(onClick = {})
        profileScreen(onClick = {})
    }
}


