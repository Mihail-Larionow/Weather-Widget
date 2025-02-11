package com.michel.settings.presentation.mvi

import com.michel.mvi.store.Reducer
import com.michel.settings.navigation.SettingsNavDirection
import com.michel.settings.presentation.mvi.entities.SettingsEffect
import com.michel.settings.presentation.mvi.entities.SettingsMessage
import com.michel.settings.presentation.mvi.entities.SettingsState
import io.kotest.matchers.shouldBe
import org.junit.Test

class SettingsReducerTest {

    private val settingsLoadingState = SettingsState.Loading

    private val settingsReducer = SettingsReducer()

    @Test
    fun `when navigate message is received then return navigation effect`() {
        settingsReducer.reduce(
            message = SettingsMessage.Navigate(SettingsNavDirection.Up),
            prevState = settingsLoadingState,
        ).shouldBe(settingsLoadingState)
        settingsReducer.reduceEffect(
            message = SettingsMessage.Navigate(SettingsNavDirection.Up),
        ).shouldBe(SettingsEffect.Navigate(SettingsNavDirection.Up))
    }

    @Test
    fun `when any other message is received then return nothing`() {
        settingsReducer.reduceEffect(
            message = SettingsMessage.Empty,
        ).shouldBe(Reducer.nothing)
    }
}
