package com.michel.weatherit.main

import android.os.Build
import android.os.Bundle
import com.michel.mvi.MviActivity
import com.michel.mvi.store.StoreViewModelFactory
import com.michel.navigation.presentation.NavFragment
import com.michel.navigation.presentation.navcontroller.NavControllerActivity
import com.michel.navigation.presentation.navcontroller.NavControllerFragment
import com.michel.weatherit.databinding.ActivityMainBinding
import com.michel.weatherit.main.mvi.entities.MainEffect
import com.michel.weatherit.main.mvi.entities.MainIntent
import com.michel.weatherit.main.mvi.entities.MainMessage
import com.michel.weatherit.main.mvi.entities.MainState
import javax.inject.Inject


class MainActivity : MviActivity<
        MainIntent,
        MainEffect,
        MainState,
        MainMessage,
        >(), NavControllerActivity {

    private lateinit var binding: ActivityMainBinding

    @Inject
    override lateinit var viewModelFactory: StoreViewModelFactory<MainIntent, MainEffect, MainState, MainMessage>

    @Inject
    lateinit var activityInitializerFactory: ActivityInitializer.Factory
    private val activityInitializer: ActivityInitializer by lazy {
        activityInitializerFactory.create(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (shouldUseSystemSplash()) {
            splashScreen.setOnExitAnimationListener { systemSplashScreen ->
                systemSplashScreen.remove()
            }
        }

        if (!::binding.isInitialized) {
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
        }
        activityInitializer.init()
    }

    override fun onResume() {
        super.onResume()
        viewModel.accept(MainIntent.Stop)
    }

    override fun render(state: MainState) {

    }

    override fun handleEffect(effect: MainEffect) {
        when (effect) {
            is MainEffect.FinishSplash -> attachNavigation()
        }
    }

    override fun getNavControllerFragment(): NavControllerFragment? =
        supportFragmentManager.fragments
            .filterIsInstance<NavControllerFragment>()
            .firstOrNull()

    private fun attachNavigation() {
        supportFragmentManager.beginTransaction()
            .replace(
                binding.mainActivityNavHost.id,
                NavFragment.newInstance(),
            )
            .commitNow()
    }

    private fun shouldUseSystemSplash(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
}
