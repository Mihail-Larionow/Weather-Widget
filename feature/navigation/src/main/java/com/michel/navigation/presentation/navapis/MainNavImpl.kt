package com.michel.navigation.presentation.navapis

import com.michel.navigation.presentation.navcontroller.WeatherNavController
import com.michel.navigation.presentation.navcontroller.WeatherNavOptions
import com.michel.navigation.presentation.navcontroller.navigate
import com.michel.navigation.presentation.navgraph.destinations.NavGraphDestination
import com.michel.navigation.presentation.navigation.MainNavDirection
import javax.inject.Inject

internal class MainNavImpl @Inject constructor(
    private val weatherNavController: WeatherNavController,
) : com.michel.navigation.base.FeatureNavApi<MainNavDirection> {
    override fun navigate(direction: MainNavDirection) {
        weatherNavController.run {
            when (direction) {
                MainNavDirection.ToWeather -> navigateToWeather()
                MainNavDirection.Up -> this@MainNavImpl.navigateUp()
            }
        }
    }

    private fun WeatherNavController.navigateToWeather() {
        navigate(
            destination = NavGraphDestination.Weather,
            options = WeatherNavOptions(
                singleTop = true,
            ),
        )
    }

    private fun navigateUp() {
        if (canNavigateUp()) {
            weatherNavController.navigateUp()
        } else {
            weatherNavController.finish()
        }
    }

    private fun canNavigateUp(): Boolean =
        weatherNavController.previousDestinationId != null
}
