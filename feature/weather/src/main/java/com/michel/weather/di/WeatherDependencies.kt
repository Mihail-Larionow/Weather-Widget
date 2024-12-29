package com.michel.weather.di

import com.michel.di.model.BaseDependencies
import com.michel.navigation.base.FeatureNavApi
import com.michel.ui.activity.ActivityRegistry
import com.michel.weather.navigation.WeatherNavDirection

interface WeatherDependencies : BaseDependencies {

    val weatherFeatureNavApi: FeatureNavApi<WeatherNavDirection>

    val activityRegistry: ActivityRegistry
}
