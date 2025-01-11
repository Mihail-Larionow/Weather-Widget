package com.michel.weather.presentation.mvi

import com.michel.mvi.store.Reducer
import com.michel.weather.presentation.mvi.entities.WeatherEffect
import com.michel.weather.presentation.mvi.entities.WeatherMessage
import com.michel.weather.presentation.mvi.entities.WeatherState
import com.michel.weather.presentation.mvi.entities.mapper.WeatherMapper
import javax.inject.Inject

class WeatherReducer @Inject constructor(
    private val weatherMapper: WeatherMapper,
) : Reducer<WeatherEffect, WeatherState, WeatherMessage> {

    override fun reduce(
        message: WeatherMessage,
        prevState: WeatherState
    ): WeatherState = when (message) {
        is WeatherMessage.WeatherDataLoaded -> weatherMapper.mapToState(message.weatherData)
        is WeatherMessage.Navigate,
        is WeatherMessage.Empty,
            -> prevState
    }


    override fun reduceEffect(message: WeatherMessage): WeatherEffect? =
        when (message) {
            is WeatherMessage.Navigate -> WeatherEffect.Navigate(message.direction)
            is WeatherMessage.WeatherDataLoaded,
            is WeatherMessage.Empty,
                -> Reducer.nothing
        }
}
