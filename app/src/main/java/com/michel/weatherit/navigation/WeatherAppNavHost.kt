package com.michel.weatherit.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import com.michel.weather.WeatherScreen
import com.michel.weather.navigation.WeatherRoute
import com.michel.weatherit.ui.WeatherAppState

@Composable
fun WeatherAppNavHost(
    modifier: Modifier = Modifier,
    appState: WeatherAppState,
    onShowSnackbar: suspend (String, String) -> Boolean,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = WeatherRoute,
        modifier = modifier,
    ) {
        
    }
}


