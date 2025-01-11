package com.michel.navigation.presentation.navapis

import com.michel.api.FeatureNavApi
import com.michel.navigation.domain.MainNavController
import com.michel.settings.navigation.SettingsNavDirection
import javax.inject.Inject

internal class SettingsNavImpl @Inject constructor(
    private val mainNavController: MainNavController,
) : FeatureNavApi<SettingsNavDirection> {
    override fun navigate(direction: SettingsNavDirection) {
        mainNavController.run {
            when (direction) {
                SettingsNavDirection.Up -> navigateUp()
            }
        }
    }
}
