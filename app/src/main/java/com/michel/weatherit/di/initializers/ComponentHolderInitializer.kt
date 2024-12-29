package com.michel.weatherit.di.initializers

import com.michel.navigation.di.NavComponentHolder
import com.michel.navigation.di.NavDependencies
import com.michel.profile.di.ProfileComponentHolder
import com.michel.profile.di.ProfileDependencies
import com.michel.weather.di.WeatherComponentHolder
import com.michel.weather.di.WeatherDependencies
import javax.inject.Inject
import javax.inject.Provider

class ComponentHolderInitializer @Inject constructor(
    private val weatherFeatureDependenciesProvider: Provider<WeatherDependencies>,
    private val profileFeatureDependenciesProvider: Provider<ProfileDependencies>,
    private val navFeatureDependenciesProvider: Provider<NavDependencies>,
    ) {

    fun initAll() {
        WeatherComponentHolder.init(weatherFeatureDependenciesProvider)
        ProfileComponentHolder.init(profileFeatureDependenciesProvider)
        NavComponentHolder.init(navFeatureDependenciesProvider)
    }
}
