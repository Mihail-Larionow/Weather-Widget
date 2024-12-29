package com.michel.weather.di

import com.michel.di.model.BaseApi
import com.michel.weather.presentation.WeatherFragment

interface WeatherApi : BaseApi {

    fun inject(fragment: WeatherFragment)
}
