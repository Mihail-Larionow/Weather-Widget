package com.michel.weather.data

import com.michel.models.WeatherData
import com.michel.weather.domain.WeatherDataRepository
import javax.inject.Inject

class WeatherDataRepositoryImpl @Inject constructor(

): WeatherDataRepository {

    override suspend fun getWeatherData(): WeatherData = WeatherData(info = "test")
}