package com.michel.deeplinks.di

import com.michel.deeplinks.data.DeeplinkHandlerImpl
import com.michel.deeplinks.data.repositories.DeeplinkRepositoryImpl
import com.michel.deeplinks.domain.DeeplinkHandler
import com.michel.deeplinks.domain.repositories.DeeplinkRepository
import com.michel.deeplinks.presentation.DeeplinkViewModel
import com.michel.deeplinks.presentation.DeeplinkViewModelFactory
import com.michel.deeplinks.presentation.mvi.DeeplinkActor
import com.michel.deeplinks.presentation.mvi.DeeplinkReducer
import com.michel.deeplinks.presentation.mvi.entities.DeeplinkEffect
import com.michel.deeplinks.presentation.mvi.entities.DeeplinkIntent
import com.michel.deeplinks.presentation.mvi.entities.DeeplinkMessage
import com.michel.deeplinks.presentation.mvi.entities.DeeplinkState
import com.michel.mvi.store.Store
import com.michel.mvi.store.StoreViewModel
import com.michel.mvi.store.store
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton
import kotlin.coroutines.EmptyCoroutineContext

@Module
internal interface DeeplinkModule {

    @Binds
    fun bindDeeplinkRepository(
        deeplinkRepository: DeeplinkRepositoryImpl,
    ): DeeplinkRepository

    @Binds
    fun bindDeeplinkHandler(
        deeplinkHandler: DeeplinkHandlerImpl,
    ): DeeplinkHandler

    companion object {
        @Singleton
        @Provides
        fun provideCoroutineScope(): CoroutineScope =
            DeeplinkComponentHolder.componentScope()

        @Provides
        fun provideDeeplinkStore(
            actor: DeeplinkActor,
            reducer: DeeplinkReducer,
        ): Store<DeeplinkIntent, DeeplinkEffect, DeeplinkState, DeeplinkMessage> =
            store(
                initialState = DeeplinkState.Handling,
                scope = CoroutineScope(EmptyCoroutineContext),
            ) {
                this.actor = actor
                this.reducer = reducer
            }

        @Provides
        fun provideViewModelFactory(store: Store<DeeplinkIntent, DeeplinkEffect, DeeplinkState, DeeplinkMessage>): DeeplinkViewModelFactory =
            DeeplinkViewModelFactory { DeeplinkViewModel(StoreViewModel(store)) }
    }
}
