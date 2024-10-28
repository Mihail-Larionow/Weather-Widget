package com.michel.weather.presentation.mvi

import com.michel.mvi.Reducer
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
    ): WeatherState = if (message is WeatherMessage.WeatherDataLoaded) {
        weatherMapper.mapToState(weatherInitData = message.weatherInitData)
    } else {
        prevState
    }


    override fun reduceEffect(message: WeatherMessage): WeatherEffect? =
        when (message) {
            is WeatherMessage.TextWasCopied -> WeatherEffect.ShowCopySnackbar
            else -> Reducer.nothing
        }
}