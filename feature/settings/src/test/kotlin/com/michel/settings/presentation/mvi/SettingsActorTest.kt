package com.michel.settings.presentation.mvi

import app.cash.turbine.test
import com.michel.settings.navigation.SettingsNavDirection
import com.michel.settings.presentation.mvi.entities.SettingsIntent
import com.michel.settings.presentation.mvi.entities.SettingsMessage
import com.michel.settings.presentation.mvi.entities.SettingsState
import com.michel.unit.turbine.runTurbineTest
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.Test

class SettingsActorTest {

    private val settingsActor = SettingsActor()

    @Test
    fun `when actor is started then return update settings list message`() = runTurbineTest {
        settingsActor.init().test {
            awaitItem().shouldBeInstanceOf<SettingsMessage.UpdateSettingsList>()
            awaitComplete()
        }
    }

    @Test
    fun `when back clicked then return navigation message`() = runTurbineTest {
        settingsActor.run(
            intent = SettingsIntent.BackClicked,
            prevState = SettingsState.Loading,
        ).test {
            val message = awaitItem()
            message.shouldBeInstanceOf<SettingsMessage.Navigate>()
            message.direction.shouldBe(SettingsNavDirection.Up)
            awaitComplete()
        }
    }
}
