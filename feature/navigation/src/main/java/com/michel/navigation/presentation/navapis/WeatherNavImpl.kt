package com.michel.navigation.presentation.navapis

import com.michel.navigation.presentation.navcontroller.WeatherNavController
import com.michel.navigation.presentation.navcontroller.navigate
import com.michel.navigation.presentation.navgraph.destinations.FragmentDestination
import com.michel.navigation.presentation.navgraph.destinations.NavGraphDestination
import com.michel.weather.navigation.WeatherNavDirection
import javax.inject.Inject

internal class WeatherNavImpl @Inject constructor(
    private val weatherNavController: WeatherNavController,
) : com.michel.navigation.base.FeatureNavApi<WeatherNavDirection> {
    override fun navigate(direction: WeatherNavDirection) {
        weatherNavController.run {
            when (direction) {
                WeatherNavDirection.Up -> navigateUp()
                WeatherNavDirection.ToProfile -> navigateToProfile()
                WeatherNavDirection.ToSettings -> navigateToSettings()
            }
        }
    }

    private fun WeatherNavController.navigateToProfile() {
        navigate(destination = NavGraphDestination.Profile)
    }

    private fun WeatherNavController.navigateToSettings() {
        navigate(destination = FragmentDestination.Settings)
    }
}
