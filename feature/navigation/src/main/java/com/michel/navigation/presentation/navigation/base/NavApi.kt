package com.michel.navigation.presentation.navigation.base

import com.michel.di.model.BaseApi
import com.michel.navigation.base.FeatureNavApi
import com.michel.navigation.di.NavPresentationSubcomponent
import com.michel.navigation.presentation.navigation.MainNavDirection
import com.michel.profile.navigation.ProfileNavDirection
import com.michel.settings.navigation.SettingsNavDirection
import com.michel.weather.navigation.WeatherNavDirection

interface NavApi : BaseApi {

    val mainNavApi: FeatureNavApi<MainNavDirection>

    val weatherNavApi: FeatureNavApi<WeatherNavDirection>

    val profileNavApi: FeatureNavApi<ProfileNavDirection>

    val settingsNavApi: FeatureNavApi<SettingsNavDirection>

    val navFeaturePresentationSubcomponent: NavPresentationSubcomponent
}
