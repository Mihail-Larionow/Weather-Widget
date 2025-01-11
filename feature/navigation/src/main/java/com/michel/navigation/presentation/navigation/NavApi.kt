package com.michel.navigation.presentation.navigation

import com.michel.api.FeatureNavApi
import com.michel.appinformation.navigation.AppInfoNavDirection
import com.michel.di.model.BaseApi
import com.michel.profile.navigation.ProfileNavDirection
import com.michel.settings.navigation.SettingsNavDirection
import com.michel.weather.navigation.WeatherNavDirection

interface NavApi : BaseApi {

    val appInfoNavApi: FeatureNavApi<AppInfoNavDirection>

    val weatherNavApi: FeatureNavApi<WeatherNavDirection>

    val profileNavApi: FeatureNavApi<ProfileNavDirection>

    val settingsNavApi: FeatureNavApi<SettingsNavDirection>
}
