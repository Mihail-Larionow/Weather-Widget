package com.michel.weather.presentation.mvi.entities.mapper

import com.michel.weather.domain.WeatherInitData
import com.michel.weather.presentation.mvi.entities.WeatherState

fun interface WeatherMapper {

    fun mapToState(
        weatherInitData: WeatherInitData?,
    ): WeatherState
}