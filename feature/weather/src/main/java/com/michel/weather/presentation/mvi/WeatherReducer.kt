package com.michel.weather.presentation.mvi

import com.michel.mvi.store.Reducer
import com.michel.weather.presentation.extensions.toButtonAction
import com.michel.weather.presentation.extensions.toButtonTitleResId
import com.michel.weather.presentation.extensions.toPresentationModel
import com.michel.weather.presentation.extensions.toTextResId
import com.michel.weather.presentation.models.SnackbarData
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
        is WeatherMessage.WeatherDataLoadFailed,
        is WeatherMessage.Navigate,
        is WeatherMessage.Empty,
            -> prevState
    }

    override fun reduceEffect(message: WeatherMessage): WeatherEffect? =
        when (message) {
            is WeatherMessage.WeatherDataLoadFailed -> WeatherEffect.ShowErrorSnackbar(message.toPresentationModel())
            is WeatherMessage.Navigate -> WeatherEffect.Navigate(message.direction)
            is WeatherMessage.WeatherDataLoaded,
            is WeatherMessage.Empty,
                -> Reducer.nothing
        }

    private fun WeatherMessage.WeatherDataLoadFailed.toPresentationModel(): SnackbarData =
        SnackbarData(
            textResId = exception.toTextResId(),
            button = SnackbarData.Button(
                titleResId = exception.toButtonTitleResId(),
                action = exception.toButtonAction(),
            )
        )
}
