package com.michel.settings.presentation.mvi

import com.michel.mvi.store.Reducer
import com.michel.settings.presentation.mvi.entities.SettingsEffect
import com.michel.settings.presentation.mvi.entities.SettingsMessage
import com.michel.settings.presentation.mvi.entities.SettingsState
import javax.inject.Inject

class SettingsReducer @Inject constructor() :
    Reducer<SettingsEffect, SettingsState, SettingsMessage> {
    override fun reduce(message: SettingsMessage, prevState: SettingsState): SettingsState =
        when (message) {
            is SettingsMessage.UpdateSettingsList -> SettingsState.Loaded(items = message.items)
            is SettingsMessage.Navigate,
            is SettingsMessage.Empty,
                -> prevState
        }

    override fun reduceEffect(message: SettingsMessage): SettingsEffect? = when (message) {
        is SettingsMessage.Navigate -> SettingsEffect.Navigate(message.direction)
        is SettingsMessage.UpdateSettingsList,
        is SettingsMessage.Empty,
            -> Reducer.nothing
    }
}
