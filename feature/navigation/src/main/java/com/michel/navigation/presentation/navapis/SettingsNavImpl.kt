package com.michel.navigation.presentation.navapis

import com.michel.navigation.presentation.navcontroller.WeatherNavController
import com.michel.navigation.base.FeatureNavApi
import com.michel.settings.navigation.SettingsNavDirection
import javax.inject.Inject

internal class SettingsNavImpl @Inject constructor(
    private val weatherNavController: WeatherNavController,
) : com.michel.navigation.base.FeatureNavApi<SettingsNavDirection> {
    override fun navigate(direction: SettingsNavDirection) {
        weatherNavController.run {
            when (direction) {
                SettingsNavDirection.Up -> navigateUp()
            }
        }
    }
}
