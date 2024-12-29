package com.michel.settings.presentation

import androidx.compose.runtime.Composable
import androidx.fragment.app.Fragment
import com.michel.mvi.MviFragment
import com.michel.mvi.store.StoreViewModelFactory
import com.michel.settings.presentation.mvi.entities.SettingsEffect
import com.michel.settings.presentation.mvi.entities.SettingsIntent
import com.michel.settings.presentation.mvi.entities.SettingsMessage
import com.michel.settings.presentation.mvi.entities.SettingsState
import javax.inject.Inject
import kotlin.reflect.KClass

val SettingsFragmentKClass: KClass<out Fragment> = SettingsFragment::class

class SettingsFragment : MviFragment<
        SettingsIntent,
        SettingsEffect,
        SettingsState,
        SettingsMessage,
        >() {

    @Inject
    override lateinit var viewModelFactory: StoreViewModelFactory<SettingsIntent, SettingsEffect, SettingsState, SettingsMessage>

    @Composable
    override fun Render(state: SettingsState) {

    }

    override fun handleEffect(effect: SettingsEffect) {
        when (effect) {
            is SettingsEffect.Navigate -> TODO()
        }
    }
}
