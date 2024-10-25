package com.michel.weatherit.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.michel.profile.navigation.ProfileRoute
import com.michel.ui.icon.WiIcons
import com.michel.weather.navigation.WeatherRoute
import com.michel.weather.R as weatherR
import com.michel.profile.R as profileR
import kotlin.reflect.KClass

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val iconTextId: Int,
    val route: KClass<*>,
) {
    WEATHER(
        selectedIcon = WiIcons.Favourite,
        unselectedIcon = WiIcons.FavouriteBorder,
        iconTextId = weatherR.string.weather_app_feature_weather_navigation_label,
        route = WeatherRoute::class,
    ),
    PROFILE(
        selectedIcon = WiIcons.Person,
        unselectedIcon = WiIcons.PersonBorder,
        iconTextId = profileR.string.weather_app_feature_profile_navigation_label,
        route = ProfileRoute::class,
    ),
}