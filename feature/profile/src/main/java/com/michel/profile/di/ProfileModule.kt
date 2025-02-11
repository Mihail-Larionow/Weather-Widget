package com.michel.profile.di

import com.michel.mvi.store.Store
import com.michel.mvi.store.StoreViewModel
import com.michel.mvi.store.StoreViewModelFactory
import com.michel.mvi.store.store
import com.michel.profile.presentation.model.ProfileHeader
import com.michel.profile.presentation.mvi.ProfileActor
import com.michel.profile.presentation.mvi.ProfileReducer
import com.michel.profile.presentation.mvi.entities.ProfileEffect
import com.michel.profile.presentation.mvi.entities.ProfileIntent
import com.michel.profile.presentation.mvi.entities.ProfileMessage
import com.michel.profile.presentation.mvi.entities.ProfileState
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton
import kotlin.coroutines.EmptyCoroutineContext

@Module
internal interface ProfileModule {

    companion object {
        @Singleton
        @Provides
        fun provideCoroutineScope(): CoroutineScope =
            ProfileComponentHolder.componentScope()

        @Provides
        fun provideProfileStore(
            actor: ProfileActor,
            reducer: ProfileReducer,
        ): Store<ProfileIntent, ProfileEffect, ProfileState, ProfileMessage> =
            store(
                initialState = ProfileState.Loaded(ProfileHeader.Authorized),
                scope = CoroutineScope(EmptyCoroutineContext),
            ) {
                this.actor = actor
                this.reducer = reducer
            }

        @Provides
        fun provideViewModelFactory(store: Store<ProfileIntent, ProfileEffect, ProfileState, ProfileMessage>): StoreViewModelFactory<ProfileIntent, ProfileEffect, ProfileState, ProfileMessage> =
            StoreViewModelFactory { StoreViewModel(store) }

    }
}
