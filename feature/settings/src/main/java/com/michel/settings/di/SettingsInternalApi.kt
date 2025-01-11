package com.michel.settings.di

import com.michel.api.FeatureNavApi
import com.michel.mvi.store.StoreViewModelFactory
import com.michel.settings.navigation.SettingsNavDirection
import com.michel.settings.presentation.mvi.entities.SettingsEffect
import com.michel.settings.presentation.mvi.entities.SettingsIntent
import com.michel.settings.presentation.mvi.entities.SettingsMessage
import com.michel.settings.presentation.mvi.entities.SettingsState

internal interface SettingsInternalApi {

    val viewModelFactory: StoreViewModelFactory<SettingsIntent, SettingsEffect, SettingsState, SettingsMessage>

    val navApi: FeatureNavApi<SettingsNavDirection>
}
