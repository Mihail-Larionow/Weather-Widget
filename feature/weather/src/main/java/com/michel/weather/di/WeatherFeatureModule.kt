package com.michel.weather.di

import com.michel.mvi.Store
import com.michel.weather.data.WeatherDataRepositoryImpl
import com.michel.weather.domain.WeatherDataRepository
import com.michel.weather.presentation.mvi.WeatherActor
import com.michel.weather.presentation.mvi.WeatherReducer
import com.michel.weather.presentation.mvi.entities.WeatherEffect
import com.michel.weather.presentation.mvi.entities.WeatherIntent
import com.michel.weather.presentation.mvi.entities.WeatherMessage
import com.michel.weather.presentation.mvi.entities.WeatherState
import com.michel.weather.presentation.mvi.entities.mapper.WeatherMapper
import com.michel.weather.presentation.mvi.entities.mapper.WeatherMapperImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object WeatherFeatureModule {

    @Provides
    fun provideWeatherStore(
        actor: WeatherActor,
        reducer: WeatherReducer,
    ): Store<WeatherIntent, WeatherEffect, WeatherState, WeatherMessage> {

        return Store(
            initialState = WeatherState(),
            actor = actor,
            reducer = reducer,
        )
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface Binder {

        @Binds
        fun bindWeatherDataRepository(
            weatherDataRepositoryImpl: WeatherDataRepositoryImpl,
        ): WeatherDataRepository

        @Binds
        fun bindWeatherMapper(
            weatherMapperImpl: WeatherMapperImpl,
        ): WeatherMapper
    }
}