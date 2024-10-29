package com.michel.network.data

import com.michel.models.WeatherData

interface WeatherNetworkDataSource {
    suspend fun getWeather(): Result<WeatherData>
}