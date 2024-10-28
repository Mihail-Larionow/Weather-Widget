package com.michel.network.data

interface WeatherNetworkDataSource {
    suspend fun getWeather()
}