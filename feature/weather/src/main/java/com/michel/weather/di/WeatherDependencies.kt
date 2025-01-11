package com.michel.weather.di

import com.michel.api.FeatureNavApi
import com.michel.api.data.NetworkDataSource
import com.michel.di.model.BaseDependencies
import com.michel.weather.navigation.WeatherNavDirection

interface WeatherDependencies : BaseDependencies {

    val networkDataSource: NetworkDataSource

    val weatherFeatureNavApi: FeatureNavApi<WeatherNavDirection>
}
