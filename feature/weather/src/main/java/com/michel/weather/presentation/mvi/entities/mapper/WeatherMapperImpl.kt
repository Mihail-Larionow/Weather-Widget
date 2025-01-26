package com.michel.weather.presentation.mvi.entities.mapper

import com.michel.weather.domain.models.WeatherDomainModel
import com.michel.weather.presentation.mvi.entities.WeatherState
import javax.inject.Inject

internal class WeatherMapperImpl @Inject constructor() : WeatherMapper {

    override fun mapToState(domainWeatherData: WeatherDomainModel): WeatherState.Loaded {
        val weatherInfo = domainWeatherData.temperature
        return WeatherState.Loaded(
            temperature = weatherInfo.map {
                it.toInt()
            },
        )
    }
}
