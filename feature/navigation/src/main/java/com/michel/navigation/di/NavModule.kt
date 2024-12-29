package com.michel.navigation.di

import com.michel.mvi.store.Store
import com.michel.mvi.store.StoreViewModel
import com.michel.mvi.store.StoreViewModelFactory
import com.michel.mvi.store.store
import com.michel.navigation.presentation.mvi.NavActor
import com.michel.navigation.presentation.mvi.NavReducer
import com.michel.navigation.presentation.mvi.entities.NavEffect
import com.michel.navigation.presentation.mvi.entities.NavIntent
import com.michel.navigation.presentation.mvi.entities.NavMessage
import com.michel.navigation.presentation.mvi.entities.NavState
import com.michel.navigation.presentation.navcontroller.WeatherNavController
import com.michel.navigation.presentation.navcontroller.WeatherNavControllerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlin.coroutines.EmptyCoroutineContext

@Module
internal interface NavModule {

    @Binds
    fun bindWeatherNavController(
        controller: WeatherNavControllerImpl,
    ): WeatherNavController

    companion object {
        @Provides
        fun provideNavStore(
            actor: NavActor,
            reducer: NavReducer,
        ): Store<NavIntent, NavEffect, NavState, NavMessage> =
            store(
                initialState = NavState(),
                scope = CoroutineScope(EmptyCoroutineContext),
            ) {
                this.actor = actor
                this.reducer = reducer
            }

        @Provides
        fun provideViewModelFactory(store: Store<NavIntent, NavEffect, NavState, NavMessage>): StoreViewModelFactory<NavIntent, NavEffect, NavState, NavMessage> =
            StoreViewModelFactory { StoreViewModel(store) }
    }
}
