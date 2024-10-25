package com.michel.weather.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.michel.weather.WeatherScreen
import kotlinx.serialization.Serializable

@Serializable
data object WeatherRoute

fun NavController.navigateToWeather(navOptions: NavOptions) =
    navigate(route = WeatherRoute, navOptions = navOptions)

fun NavGraphBuilder.weatherScreen(onClick: () -> Unit) {
    composable<WeatherRoute> {
        WeatherScreen()
    }
}