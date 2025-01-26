package com.michel.settings.di

import com.michel.mvi.store.Store
import com.michel.mvi.store.StoreViewModel
import com.michel.mvi.store.StoreViewModelFactory
import com.michel.mvi.store.store
import com.michel.settings.presentation.mvi.SettingsActor
import com.michel.settings.presentation.mvi.SettingsReducer
import com.michel.settings.presentation.mvi.entities.SettingsEffect
import com.michel.settings.presentation.mvi.entities.SettingsIntent
import com.michel.settings.presentation.mvi.entities.SettingsMessage
import com.michel.settings.presentation.mvi.entities.SettingsState
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton
import kotlin.coroutines.EmptyCoroutineContext

@Module
internal interface SettingsModule {

    companion object {
        @Singleton
        @Provides
        fun provideCoroutineScope(): CoroutineScope =
            SettingsComponentHolder.componentScope()

        @Provides
        fun provideSettingsStore(
            actor: SettingsActor,
            reducer: SettingsReducer,
        ): Store<SettingsIntent, SettingsEffect, SettingsState, SettingsMessage> =
            store(
                initialState = SettingsState.Loading,
                scope = CoroutineScope(EmptyCoroutineContext),
            ) {
                this.actor = actor
                this.reducer = reducer
            }

        @Provides
        fun provideViewModelFactory(store: Store<SettingsIntent, SettingsEffect, SettingsState, SettingsMessage>): StoreViewModelFactory<SettingsIntent, SettingsEffect, SettingsState, SettingsMessage> =
            StoreViewModelFactory { StoreViewModel(store) }
    }
}
