package com.michel.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.michel.profile.ProfileScreen
import kotlinx.serialization.Serializable

@Serializable
data object ProfileRoute

fun NavController.navigateToProfile(navOptions: NavOptions) =
    navigate(route = ProfileRoute, navOptions = navOptions)

fun NavGraphBuilder.profileScreen(onClick: () -> Unit) {
    composable<ProfileRoute> {
        ProfileScreen()
    }
}