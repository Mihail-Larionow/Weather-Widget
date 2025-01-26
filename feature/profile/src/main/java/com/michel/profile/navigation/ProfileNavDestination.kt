package com.michel.profile.navigation

import androidx.compose.runtime.Composable
import com.michel.api.NavDestination
import com.michel.api.RouteSpec
import com.michel.api.routeSpec
import com.michel.profile.presentation.composables.Profile

object ProfileNavDestination : NavDestination<ProfileRoute> {
    override val routeSpec: RouteSpec<ProfileRoute> = routeSpec()

    @Composable
    override fun Content(route: ProfileRoute) {
        Profile()
    }
}
