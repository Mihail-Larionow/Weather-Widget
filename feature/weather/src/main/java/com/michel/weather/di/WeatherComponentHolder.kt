package com.michel.weather.di

import com.michel.di.holder.ComponentHolder
import kotlinx.coroutines.cancel

object WeatherComponentHolder : ComponentHolder<
        WeatherApi,
        WeatherDependencies,
        >() {

    private fun getInternalApi(): WeatherInternalApi =
        get() as WeatherInternalApi

    override fun build(
        dependencies: WeatherDependencies,
    ): WeatherApi = DaggerWeatherComponent.factory().create(dependencies)

    override fun beforeClear() {
        getInternalApi().coroutineScope.cancel()
    }
}
