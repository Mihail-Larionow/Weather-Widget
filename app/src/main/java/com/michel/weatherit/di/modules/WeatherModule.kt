package com.michel.weatherit.di.modules

import com.michel.navigation.base.FeatureNavApi
import com.michel.navigation.presentation.navigation.base.NavApi
import com.michel.ui.activity.ActivityRegistry
import com.michel.weather.di.WeatherDependencies
import com.michel.weather.domain.WeatherDataRepository
import com.michel.weather.navigation.WeatherNavDirection
import dagger.Module
import dagger.Provides

@Module
object WeatherModule {

    @Provides
    fun provideWeatherDependencies(
        navApi: NavApi,
        activityRegistry: ActivityRegistry,
    ): WeatherDependencies = object : WeatherDependencies {

        override val weatherFeatureNavApi: FeatureNavApi<WeatherNavDirection> = navApi.weatherNavApi

        override val activityRegistry: ActivityRegistry = activityRegistry
    }
}
