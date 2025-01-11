package com.michel.settings.presentation.mvi

import com.michel.mvi.store.Actor
import com.michel.settings.domain.model.SettingsItem
import com.michel.settings.domain.model.ToggleType
import com.michel.settings.navigation.SettingsNavDirection
import com.michel.settings.presentation.mvi.entities.SettingsIntent
import com.michel.settings.presentation.mvi.entities.SettingsMessage
import com.michel.settings.presentation.mvi.entities.SettingsState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SettingsActor @Inject constructor() : Actor<SettingsIntent, SettingsState, SettingsMessage> {

    override fun init(): Flow<SettingsMessage> = flowOf(
        SettingsMessage.UpdateSettingsList(items = getSettingsList())
    )

    override fun run(intent: SettingsIntent, prevState: SettingsState): Flow<SettingsMessage> =
        when (intent) {
            is SettingsIntent.BackClicked -> flowOf(SettingsMessage.Navigate(SettingsNavDirection.Up))
            is SettingsIntent.ToggleStateUpdated -> updateToggleState()
        }

    private fun getSettingsList(): List<SettingsItem> = buildList {
        addThemeToggle()
    }

    private fun MutableList<SettingsItem>.addThemeToggle() {
        add(
            SettingsItem.Toggle(
                ToggleType.THEME,
                true,
            )
        )
    }

    private fun updateToggleState(): Flow<SettingsMessage> = flow {
        emit(SettingsMessage.Empty)
    }
}
