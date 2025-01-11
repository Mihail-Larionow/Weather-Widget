package com.michel.appinformation.di

import com.michel.appinformation.presentation.mvi.AppInfoActor
import com.michel.appinformation.presentation.mvi.AppInfoReducer
import com.michel.appinformation.presentation.mvi.entities.AppInfoEffect
import com.michel.appinformation.presentation.mvi.entities.AppInfoIntent
import com.michel.appinformation.presentation.mvi.entities.AppInfoMessage
import com.michel.appinformation.presentation.mvi.entities.AppInfoState
import com.michel.mvi.store.Store
import com.michel.mvi.store.StoreViewModel
import com.michel.mvi.store.StoreViewModelFactory
import com.michel.mvi.store.store
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton
import kotlin.coroutines.EmptyCoroutineContext

@Module
internal interface AppInfoModule {

    companion object {
        @Singleton
        @Provides
        fun provideCoroutineScope(): CoroutineScope =
            AppInfoComponentHolder.componentScope()

        @Provides
        fun provideAppInfoStore(
            actor: AppInfoActor,
            reducer: AppInfoReducer,
        ): Store<AppInfoIntent, AppInfoEffect, AppInfoState, AppInfoMessage> =
            store(
                initialState = AppInfoState(),
                scope = CoroutineScope(EmptyCoroutineContext),
            ) {
                this.actor = actor
                this.reducer = reducer
            }

        @Provides
        fun provideViewModelFactory(store: Store<AppInfoIntent, AppInfoEffect, AppInfoState, AppInfoMessage>): StoreViewModelFactory<AppInfoIntent, AppInfoEffect, AppInfoState, AppInfoMessage> =
            StoreViewModelFactory { StoreViewModel(store) }
    }
}
