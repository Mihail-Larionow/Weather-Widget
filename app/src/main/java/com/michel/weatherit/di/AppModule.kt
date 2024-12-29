package com.michel.weatherit.di

import com.michel.mvi.store.Store
import com.michel.mvi.store.StoreViewModel
import com.michel.mvi.store.StoreViewModelFactory
import com.michel.mvi.store.store
import com.michel.navigation.di.NavComponentHolder
import com.michel.navigation.di.NavDependencies
import com.michel.navigation.presentation.navigation.base.NavApi
import com.michel.network.di.NetworkModule
import com.michel.ui.activity.ActivityRegistry
import com.michel.weatherit.di.modules.ProfileModule
import com.michel.weatherit.di.modules.WeatherModule
import com.michel.weatherit.main.mvi.MainActor
import com.michel.weatherit.main.mvi.MainReducer
import com.michel.weatherit.main.mvi.entities.MainEffect
import com.michel.weatherit.main.mvi.entities.MainIntent
import com.michel.weatherit.main.mvi.entities.MainMessage
import com.michel.weatherit.main.mvi.entities.MainState
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import javax.inject.Singleton
import kotlin.coroutines.EmptyCoroutineContext


@Module(
    includes = [
        WeatherModule::class,
        ProfileModule::class,
        NetworkModule::class,
    ]
)
class AppModule {

    @Provides
    fun provideNavFeatureApi(): NavApi = NavComponentHolder.get()

    @Singleton
    @Provides
    fun provideCoroutineScope(): CoroutineScope = CoroutineScope(Dispatchers.IO)

    @Provides
    fun provideNavFeatureDependencies(
        activityRegistry: ActivityRegistry,
    ): NavDependencies = object : NavDependencies {
        override val activityRegistry: ActivityRegistry = activityRegistry
    }
}
