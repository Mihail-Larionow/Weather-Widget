package com.michel.weatherit.di.modules.feature

import com.michel.api.FeatureNavApi
import com.michel.api.data.NetworkDataSource
import com.michel.navigation.presentation.navigation.NavApi
import com.michel.weather.di.WeatherDependencies
import com.michel.weather.navigation.WeatherNavDirection
import dagger.Module
import dagger.Provides

@Module
object WeatherModule {

    @Provides
    fun provideWeatherDependencies(
        networkDataSource: NetworkDataSource,
        navApi: NavApi,
    ): WeatherDependencies = object : WeatherDependencies {

        override val networkDataSource: NetworkDataSource = networkDataSource

        override val weatherFeatureNavApi: FeatureNavApi<WeatherNavDirection> = navApi.weatherNavApi
    }
}
