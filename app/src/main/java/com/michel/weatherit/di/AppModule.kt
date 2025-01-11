package com.michel.weatherit.di

import com.michel.impl.di.NetworkApi
import com.michel.impl.di.NetworkComponentHolder
import com.michel.navigation.di.NavComponentHolder
import com.michel.navigation.di.NavDependencies
import com.michel.navigation.presentation.navigation.NavApi
import com.michel.weatherit.di.modules.core.NetworkModule
import com.michel.weatherit.di.modules.feature.AppInfoModule
import com.michel.weatherit.di.modules.feature.ProfileModule
import com.michel.weatherit.di.modules.feature.SettingsModule
import com.michel.weatherit.di.modules.feature.WeatherModule
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@Module(
    includes = [
        WeatherModule::class,
        AppInfoModule::class,
        ProfileModule::class,
        SettingsModule::class,
        NetworkModule::class,
    ]
)
class AppModule {

    @Provides
    fun provideNetworkApi(): NetworkApi = NetworkComponentHolder.get()

    @Provides
    fun provideNavFeatureApi(): NavApi = NavComponentHolder.get()

    @Singleton
    @Provides
    fun provideCoroutineScope(): CoroutineScope = CoroutineScope(Dispatchers.IO)

    @Provides
    fun provideNavFeatureDependencies(
    ): NavDependencies = object : NavDependencies {}
}
