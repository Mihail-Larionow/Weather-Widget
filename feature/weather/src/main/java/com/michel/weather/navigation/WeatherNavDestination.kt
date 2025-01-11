package com.michel.weather.navigation

import androidx.compose.runtime.Composable
import com.michel.api.NavDestination
import com.michel.api.RouteSpec
import com.michel.api.routeSpec
import com.michel.weather.presentation.WeatherScreen

object WeatherNavDestination : NavDestination<WeatherRoute> {
    override val routeSpec: RouteSpec<WeatherRoute> = routeSpec()

    @Composable
    override fun Content(route: WeatherRoute) {
        WeatherScreen()
    }
}
