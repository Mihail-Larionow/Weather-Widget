package com.michel.navigation.di

import com.michel.navigation.presentation.navapis.MainNavImpl
import com.michel.navigation.presentation.navapis.ProfileNavImpl
import com.michel.navigation.presentation.navapis.SettingsNavImpl
import com.michel.navigation.presentation.navapis.WeatherNavImpl
import com.michel.navigation.base.FeatureNavApi
import com.michel.navigation.presentation.navigation.MainNavDirection
import com.michel.profile.navigation.ProfileNavDirection
import com.michel.settings.navigation.SettingsNavDirection
import com.michel.weather.navigation.WeatherNavDirection
import dagger.Binds
import dagger.Module

@Module
internal interface NavApiModule {

    @Binds
    fun bindMainNavApi(impl: MainNavImpl): com.michel.navigation.base.FeatureNavApi<MainNavDirection>

    @Binds
    fun bindWeatherNavApi(impl: WeatherNavImpl): com.michel.navigation.base.FeatureNavApi<WeatherNavDirection>

    @Binds
    fun bindProfileNavApi(impl: ProfileNavImpl): com.michel.navigation.base.FeatureNavApi<ProfileNavDirection>

    @Binds
    fun bindSettingsNavApi(impl: SettingsNavImpl): com.michel.navigation.base.FeatureNavApi<SettingsNavDirection>
}
