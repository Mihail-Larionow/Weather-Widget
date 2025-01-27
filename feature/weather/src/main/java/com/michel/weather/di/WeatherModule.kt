package com.michel.weather.di

import com.michel.mvi.store.Store
import com.michel.mvi.store.StoreViewModel
import com.michel.mvi.store.StoreViewModelFactory
import com.michel.mvi.store.store
import com.michel.weather.data.repositories.WeatherDataRepositoryImpl
import com.michel.weather.domain.repositories.WeatherDataRepository
import com.michel.weather.presentation.mvi.WeatherActor
import com.michel.weather.presentation.mvi.WeatherReducer
import com.michel.weather.presentation.mvi.entities.WeatherEffect
import com.michel.weather.presentation.mvi.entities.WeatherIntent
import com.michel.weather.presentation.mvi.entities.WeatherMessage
import com.michel.weather.presentation.mvi.entities.WeatherState
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton
import kotlin.coroutines.EmptyCoroutineContext

@Module
internal interface WeatherModule {

    @Binds
    fun bindWeatherDataRepository(
        weatherDataRepositoryImpl: WeatherDataRepositoryImpl,
    ): WeatherDataRepository

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
                initialState = WeatherState.Loading,
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
