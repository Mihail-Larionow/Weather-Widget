package com.michel.weather.di

import com.michel.mvi.store.Store
import com.michel.mvi.store.StoreViewModel
import com.michel.mvi.store.StoreViewModelFactory
import com.michel.mvi.store.store
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import javax.inject.Singleton
import kotlin.coroutines.EmptyCoroutineContext

@Module
internal interface WeatherModule {

    @Binds
    fun bindWeatherDataRepository(
        weatherDataRepositoryImpl: WeatherDataRepositoryImpl,
    ): WeatherDataRepository

    @Binds
    fun bindWeatherMapper(
        weatherMapperImpl: WeatherMapperImpl,
    ): WeatherMapper

    companion object {
        @Singleton
        @Provides
        fun provideCoroutineScope(): CoroutineScope =
            WeatherComponentHolder.componentScope()

        @Provides
        fun provideWeatherStore(
            actor: WeatherActor,
            reducer: WeatherReducer,
        ): Store<WeatherIntent, WeatherEffect, WeatherState, WeatherMessage> =
            store(
                initialState = WeatherState(),
                scope = CoroutineScope(EmptyCoroutineContext),
            ) {
                this.actor = actor
                this.reducer = reducer
            }

        @Provides
        fun provideViewModelFactory(store: Store<WeatherIntent, WeatherEffect, WeatherState, WeatherMessage>): StoreViewModelFactory<WeatherIntent, WeatherEffect, WeatherState, WeatherMessage> =
            StoreViewModelFactory { StoreViewModel(store) }

    }
}
