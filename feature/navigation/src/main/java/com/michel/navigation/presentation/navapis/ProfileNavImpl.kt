package com.michel.navigation.presentation.navapis

import com.michel.navigation.presentation.navcontroller.WeatherNavController
import com.michel.navigation.base.FeatureNavApi
import com.michel.profile.navigation.ProfileNavDirection
import javax.inject.Inject

internal class ProfileNavImpl @Inject constructor(
    private val weatherNavController: WeatherNavController,
) : FeatureNavApi<ProfileNavDirection> {
    override fun navigate(direction: ProfileNavDirection) {
        weatherNavController.run {
            when (direction) {
                ProfileNavDirection.Up -> navigateUp()
            }
        }
    }
}
