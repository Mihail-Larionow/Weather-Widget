package com.michel.weatherit.di.initializers

import com.michel.appinformation.di.AppInfoComponentHolder
import com.michel.appinformation.di.AppInfoDependencies
import com.michel.impl.di.NetworkComponentHolder
import com.michel.impl.di.NetworkDependencies
import com.michel.navigation.di.NavComponentHolder
import com.michel.navigation.di.NavDependencies
import com.michel.profile.di.ProfileComponentHolder
import com.michel.profile.di.ProfileDependencies
import com.michel.settings.di.SettingsComponentHolder
import com.michel.settings.di.SettingsDependencies
import com.michel.weather.di.WeatherComponentHolder
import com.michel.weather.di.WeatherDependencies
import javax.inject.Inject
import javax.inject.Provider

class ComponentHolderInitializer @Inject constructor(
    private val networkDependenciesProvider: Provider<NetworkDependencies>,
    private val settingsFeatureDependenciesProvider: Provider<SettingsDependencies>,
    private val weatherFeatureDependenciesProvider: Provider<WeatherDependencies>,
    private val profileFeatureDependenciesProvider: Provider<ProfileDependencies>,
    private val appInfoFeatureDependenciesProvider: Provider<AppInfoDependencies>,
    private val navFeatureDependenciesProvider: Provider<NavDependencies>,
    ) {

    fun initAll() {
        NetworkComponentHolder.init(networkDependenciesProvider)
        SettingsComponentHolder.init(settingsFeatureDependenciesProvider)
        WeatherComponentHolder.init(weatherFeatureDependenciesProvider)
        ProfileComponentHolder.init(profileFeatureDependenciesProvider)
        AppInfoComponentHolder.init(appInfoFeatureDependenciesProvider)
        NavComponentHolder.init(navFeatureDependenciesProvider)
    }
}
