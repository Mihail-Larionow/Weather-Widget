package com.michel.weather.data

import android.util.Log
import com.michel.models.WeatherData
import com.michel.network.data.WeatherNetworkDataSource
import com.michel.weather.domain.WeatherDataRepository
import javax.inject.Inject

class WeatherDataRepositoryImpl @Inject constructor(
    //private val weatherNetworkDataSource: WeatherNetworkDataSource,
): WeatherDataRepository {

    override suspend fun getWeatherData(): WeatherData {
//        val weatherData = weatherNetworkDataSource.getWeather()
//        Log.i("WiApp", weatherData.toString())
        return WeatherData(latitude = 53.3)
    }
}
