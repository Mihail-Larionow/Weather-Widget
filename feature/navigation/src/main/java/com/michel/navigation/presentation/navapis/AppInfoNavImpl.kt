package com.michel.navigation.presentation.navapis

import com.michel.api.FeatureNavApi
import com.michel.appinformation.navigation.AppInfoNavDirection
import com.michel.navigation.domain.MainNavController
import javax.inject.Inject

internal class AppInfoNavImpl @Inject constructor(
    private val mainNavController: MainNavController,
) : FeatureNavApi<AppInfoNavDirection> {
    override fun navigate(direction: AppInfoNavDirection) {
        mainNavController.run {
            when (direction) {
                AppInfoNavDirection.Up -> navigateUp()
            }
        }
    }
}
