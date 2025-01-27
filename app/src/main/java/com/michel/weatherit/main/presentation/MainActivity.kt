package com.michel.weatherit.main.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.Composable
import com.michel.deeplinks.presentation.DeeplinkHost
import com.michel.designsystem.composables.snackbar.SnackbarHost
import com.michel.mvi.MviActivity
import com.michel.mvi.store.StoreViewModelFactory
import com.michel.navigation.presentation.NavigationHost
import com.michel.weatherit.main.presentation.mvi.entities.MainEffect
import com.michel.weatherit.main.presentation.mvi.entities.MainIntent
import com.michel.weatherit.main.presentation.mvi.entities.MainMessage
import com.michel.weatherit.main.presentation.mvi.entities.MainState
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

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleSystemIntent(intent)
    }

    @Composable
    override fun Render(state: MainState) {
        SnackbarHost {
            NavigationHost()
            DeeplinkHost()
        }
    }

    override fun handleEffect(effect: MainEffect) {
        Log.i("testtag", "ef = $effect")

        when (effect) {
            is MainEffect.FinishSplash -> handleSystemIntent(intent)
        }
    }

    private fun handleSystemIntent(intent: Intent?) {
        intent?.let {
            Log.i("testtag", "intent = okay")
            viewModel.accept(MainIntent.HandleSystemIntent(intent))
        }
    }
}
