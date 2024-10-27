package com.michel.weather.di

import com.michel.weather.domain.WeatherDataRepository
import dagger.Binds
import dagger.Module

@Module
internal object WeatherFeatureModule {

    @Module
    interface Binder {

        @Binds
        fun bindWeatherDataRepository(
            weatherDataRepositoryImpl: WeatherDataRepository
        ) : WeatherDataRepository
    }
}