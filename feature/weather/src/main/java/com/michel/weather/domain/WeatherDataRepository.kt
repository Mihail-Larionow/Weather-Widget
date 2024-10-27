package com.michel.weather.domain

import com.michel.models.WeatherData

interface WeatherDataRepository {

    suspend fun getWeatherData(): WeatherData
}