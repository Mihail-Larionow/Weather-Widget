package com.michel.weather.presentation.mvi.entities.mapper

import com.michel.weather.domain.WeatherInitData
import com.michel.weather.presentation.mvi.entities.WeatherState
import javax.inject.Inject

internal class WeatherMapperImpl @Inject constructor() : WeatherMapper {

    override fun mapToState(weatherInitData: WeatherInitData?): WeatherState {
        if(weatherInitData == null) return WeatherState()
        val weatherInfo = weatherInitData.weatherData.info
        return WeatherState(weatherInfo = weatherInfo)
    }
}