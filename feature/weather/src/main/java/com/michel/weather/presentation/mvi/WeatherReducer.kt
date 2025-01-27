package com.michel.weather.presentation.mvi

import com.michel.mvi.store.Reducer
import com.michel.weather.presentation.extensions.toPresentationModel
import com.michel.weather.presentation.mvi.entities.WeatherEffect
import com.michel.weather.presentation.mvi.entities.WeatherMessage
import com.michel.weather.presentation.mvi.entities.WeatherState
import javax.inject.Inject

class WeatherReducer @Inject constructor() : Reducer<WeatherEffect, WeatherState, WeatherMessage> {

    override fun reduce(
        message: WeatherMessage,
        prevState: WeatherState
    ): WeatherState = when (message) {
        is WeatherMessage.WeatherDataLoaded -> message.weatherData.toPresentationModel()
        is WeatherMessage.ShowInfoSnackbar,
        is WeatherMessage.Navigate,
        is WeatherMessage.Empty,
            -> prevState
    }

    override fun reduceEffect(message: WeatherMessage): WeatherEffect? =
        when (message) {
            is WeatherMessage.ShowInfoSnackbar -> WeatherEffect.ShowInfoSnackbar(message.text)
            is WeatherMessage.Navigate -> WeatherEffect.Navigate(message.direction)
            is WeatherMessage.WeatherDataLoaded,
            is WeatherMessage.Empty,
                -> Reducer.nothing
        }
}
