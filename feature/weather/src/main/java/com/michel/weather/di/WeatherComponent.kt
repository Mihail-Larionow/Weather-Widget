package com.michel.weather.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        WeatherModule::class
    ],
    dependencies = [
        WeatherDependencies::class,
    ],
)
internal interface WeatherComponent : WeatherApi, WeatherInternalApi {

    @Component.Factory
    interface Factory {
        fun create(
            dependencies: WeatherDependencies,
        ): WeatherComponent
    }
}
