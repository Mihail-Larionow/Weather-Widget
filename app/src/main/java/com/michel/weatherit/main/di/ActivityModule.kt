package com.michel.weatherit.main.di

import com.michel.mvi.store.Store
import com.michel.mvi.store.StoreViewModel
import com.michel.mvi.store.StoreViewModelFactory
import com.michel.mvi.store.store
import com.michel.weatherit.main.MainActivity
import com.michel.weatherit.main.mvi.MainActor
import com.michel.weatherit.main.mvi.MainReducer
import com.michel.weatherit.main.mvi.entities.MainEffect
import com.michel.weatherit.main.mvi.entities.MainIntent
import com.michel.weatherit.main.mvi.entities.MainMessage
import com.michel.weatherit.main.mvi.entities.MainState
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlin.coroutines.EmptyCoroutineContext

@Module(
    subcomponents = [ActivitySubcomponent::class],
)
interface ActivityModule {

    @Binds
    @IntoMap
    @ClassKey(MainActivity::class)
    fun bindActivityInjectorFactory(factory: ActivitySubcomponent.Factory): AndroidInjector.Factory<*>

    companion object {
        @Provides
        fun provideMainStore(
            actor: MainActor,
            reducer: MainReducer,
        ): Store<MainIntent, MainEffect, MainState, MainMessage> =
            store(
                initialState = MainState.Loading,
                scope = CoroutineScope(EmptyCoroutineContext),
            ) {
                this.actor = actor
                this.reducer = reducer
            }

        @Provides
        fun provideViewModelFactory(store: Store<MainIntent, MainEffect, MainState, MainMessage>): StoreViewModelFactory<MainIntent, MainEffect, MainState, MainMessage> =
            StoreViewModelFactory { StoreViewModel(store) }
    }
}
