package com.michel.weather.presentation.extensions

import com.michel.weather.domain.models.Weather
import com.michel.weather.presentation.mvi.entities.WeatherState

internal fun Weather.toPresentationModel(): WeatherState =
    WeatherState.Loaded(
        temperature = temperature,
    )
