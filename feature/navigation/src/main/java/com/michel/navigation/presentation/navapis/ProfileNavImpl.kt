package com.michel.navigation.presentation.navapis

import com.michel.api.FeatureNavApi
import com.michel.appinformation.navigation.AppInfoRoute
import com.michel.navigation.domain.MainNavController
import com.michel.profile.navigation.ProfileNavDirection
import com.michel.settings.navigation.SettingsRoute
import javax.inject.Inject

internal class ProfileNavImpl @Inject constructor(
    private val mainNavController: MainNavController,
) : FeatureNavApi<ProfileNavDirection> {

    override fun navigate(direction: ProfileNavDirection) {
        mainNavController.run {
            when (direction) {
                is ProfileNavDirection.Up -> navigateUp()
                is ProfileNavDirection.AppInfo -> navigate(AppInfoRoute)
                is ProfileNavDirection.Settings -> navigate(SettingsRoute)
            }
        }
    }
}
