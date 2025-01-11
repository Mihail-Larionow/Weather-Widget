package com.michel.weather.di

import com.michel.di.holder.ComponentHolder

object WeatherComponentHolder : ComponentHolder<WeatherApi, WeatherDependencies>() {

    internal val internalApi: WeatherInternalApi
        get() = get() as WeatherInternalApi

    override fun build(dependencies: WeatherDependencies): WeatherApi =
        DaggerWeatherComponent.factory().create(dependencies)
}
