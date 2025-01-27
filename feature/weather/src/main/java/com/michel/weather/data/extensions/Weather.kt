package com.michel.weather.data.extensions

import com.michel.weather.data.models.GetWeatherDataResponse
import com.michel.weather.domain.models.Weather

internal fun GetWeatherDataResponse.toDomainModel() = Weather(
    temperature = hourly.temperature2m.map { it.toInt() },
)
