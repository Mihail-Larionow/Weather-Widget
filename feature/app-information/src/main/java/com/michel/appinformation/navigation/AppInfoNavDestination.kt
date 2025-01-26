package com.michel.appinformation.navigation

import androidx.compose.runtime.Composable
import com.michel.api.NavDestination
import com.michel.api.RouteSpec
import com.michel.api.routeSpec
import com.michel.appinformation.presentation.composables.AppInfo

object AppInfoNavDestination : NavDestination<AppInfoRoute> {
    override val routeSpec: RouteSpec<AppInfoRoute> = routeSpec()

    @Composable
    override fun Content(route: AppInfoRoute) {
        AppInfo()
    }
}
