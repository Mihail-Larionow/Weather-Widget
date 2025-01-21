package com.michel.navigation.presentation.navapis

import com.michel.api.FeatureNavApi
import com.michel.navigation.domain.MainNavController
import com.michel.profile.navigation.ProfileRoute
import com.michel.settings.navigation.SettingsRoute
import com.michel.weather.navigation.WeatherNavDirection
import javax.inject.Inject

internal class WeatherNavImpl @Inject constructor(
    private val mainNavController: MainNavController,
) : FeatureNavApi<WeatherNavDirection> {

    override fun navigate(direction: WeatherNavDirection) {
        mainNavController.run {
            when (direction) {
                is WeatherNavDirection.Up -> navigateUp()
                is WeatherNavDirection.ToProfile -> navigate(ProfileRoute)
                is WeatherNavDirection.ToSettings -> navigate(SettingsRoute)
            }
        }
    }
}
