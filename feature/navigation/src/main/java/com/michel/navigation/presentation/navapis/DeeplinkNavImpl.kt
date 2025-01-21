package com.michel.navigation.presentation.navapis

import com.michel.api.FeatureNavApi
import com.michel.appinformation.navigation.AppInfoRoute
import com.michel.deeplinks.navigation.DeeplinkNavDirection
import com.michel.navigation.domain.MainNavController
import com.michel.profile.navigation.ProfileRoute
import javax.inject.Inject

internal class DeeplinkNavImpl @Inject constructor(
    private val mainNavController: MainNavController,
) : FeatureNavApi<DeeplinkNavDirection> {

    override fun navigate(direction: DeeplinkNavDirection) {
        mainNavController.run {
            when (direction) {
                is DeeplinkNavDirection.ToAppInfo -> navigate(AppInfoRoute)
                is DeeplinkNavDirection.ToProfile -> navigate(ProfileRoute)
            }
        }
    }
}
