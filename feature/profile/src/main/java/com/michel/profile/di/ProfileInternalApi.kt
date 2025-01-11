package com.michel.profile.di

import com.michel.api.FeatureNavApi
import com.michel.mvi.store.StoreViewModelFactory
import com.michel.profile.navigation.ProfileNavDirection
import com.michel.profile.presentation.mvi.entities.ProfileEffect
import com.michel.profile.presentation.mvi.entities.ProfileIntent
import com.michel.profile.presentation.mvi.entities.ProfileMessage
import com.michel.profile.presentation.mvi.entities.ProfileState

internal interface ProfileInternalApi {

    val viewModelFactory: StoreViewModelFactory<ProfileIntent, ProfileEffect, ProfileState, ProfileMessage>

    val navApi: FeatureNavApi<ProfileNavDirection>
}
