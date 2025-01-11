package com.michel.weather.presentation.mvi.entities.mapper

import com.michel.weather.domain.models.WeatherDomainModel
import com.michel.weather.presentation.mvi.entities.WeatherState

fun interface WeatherMapper {

    fun mapToState(
        domainWeatherData: WeatherDomainModel,
    ): WeatherState.Loaded
}
