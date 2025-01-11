package com.michel.navigation.domain

import com.michel.api.DestinationsDataSource
import com.michel.api.NavRoute
import com.michel.api.NavDestination
import com.michel.appinformation.navigation.AppInfoNavDestination
import com.michel.profile.navigation.ProfileNavDestination
import com.michel.settings.navigation.SettingsNavDestination
import com.michel.weather.navigation.WeatherNavDestination
import javax.inject.Inject

internal class MainDestinationDataSource @Inject constructor() :
    DestinationsDataSource<NavRoute> {

    override fun invoke(): Set<NavDestination<out NavRoute>> = setOf(
        WeatherNavDestination,
        SettingsNavDestination,
        ProfileNavDestination,
        AppInfoNavDestination,
    )
}
