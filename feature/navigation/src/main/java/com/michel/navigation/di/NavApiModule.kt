package com.michel.navigation.di

import com.michel.api.FeatureNavApi
import com.michel.appinformation.navigation.AppInfoNavDirection
import com.michel.navigation.presentation.navapis.AppInfoNavImpl
import com.michel.navigation.presentation.navapis.ProfileNavImpl
import com.michel.navigation.presentation.navapis.SettingsNavImpl
import com.michel.navigation.presentation.navapis.WeatherNavImpl
import com.michel.profile.navigation.ProfileNavDirection
import com.michel.settings.navigation.SettingsNavDirection
import com.michel.weather.navigation.WeatherNavDirection
import dagger.Binds
import dagger.Module

@Module
internal interface NavApiModule {

    @Binds
    fun bindAppInfoNavApi(impl: AppInfoNavImpl): FeatureNavApi<AppInfoNavDirection>

    @Binds
    fun bindWeatherNavApi(impl: WeatherNavImpl): FeatureNavApi<WeatherNavDirection>

    @Binds
    fun bindProfileNavApi(impl: ProfileNavImpl): FeatureNavApi<ProfileNavDirection>

    @Binds
    fun bindSettingsNavApi(impl: SettingsNavImpl): FeatureNavApi<SettingsNavDirection>
}
