package com.michel.weatherit.main

import android.os.Bundle
import androidx.compose.runtime.Composable
import com.michel.mvi.MviActivity
import com.michel.mvi.store.StoreViewModelFactory
import com.michel.navigation.presentation.MainNav
import com.michel.weatherit.main.mvi.entities.MainEffect
import com.michel.weatherit.main.mvi.entities.MainIntent
import com.michel.weatherit.main.mvi.entities.MainMessage
import com.michel.weatherit.main.mvi.entities.MainState
import javax.inject.Inject

class MainActivity : MviActivity<MainIntent, MainEffect, MainState, MainMessage>() {

    @Inject
    override lateinit var viewModelFactory: StoreViewModelFactory<MainIntent, MainEffect, MainState, MainMessage>

    @Inject
    lateinit var activityInitializerFactory: ActivityInitializer.Factory
    private val activityInitializer: ActivityInitializer by lazy {
        activityInitializerFactory.create(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityInitializer.init()
    }

    @Composable
    override fun Render(state: MainState) {
        MainNav()
    }

    override fun handleEffect(effect: MainEffect) {
        when (effect) {
            is MainEffect.FinishSplash -> {

            }
        }
    }
}
