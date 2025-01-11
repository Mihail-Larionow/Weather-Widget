package com.michel.settings.navigation

import androidx.compose.runtime.Composable
import com.michel.api.NavDestination
import com.michel.api.RouteSpec
import com.michel.api.routeSpec
import com.michel.settings.presentation.SettingsScreen

object SettingsNavDestination : NavDestination<SettingsRoute> {
    override val routeSpec: RouteSpec<SettingsRoute> = routeSpec()

    @Composable
    override fun Content(route: SettingsRoute) {
        SettingsScreen()
    }
}
