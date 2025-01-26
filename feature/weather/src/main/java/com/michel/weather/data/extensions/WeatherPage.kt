package com.michel.weather.data.extensions

import com.michel.weather.data.models.GetWeatherDataResponse
import com.michel.weather.domain.models.WeatherDomainModel

internal fun GetWeatherDataResponse.toWeatherDomainModel() = WeatherDomainModel(
    temperature = hourly.temperature2m.toList(),
)
