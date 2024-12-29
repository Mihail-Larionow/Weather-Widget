package com.michel.weather.presentation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.fragment.app.Fragment
import com.michel.mvi.MviFragment
import com.michel.mvi.store.StoreViewModelFactory
import com.michel.navigation.base.FeatureNavApi
import com.michel.utils.extensions.lazyUnsafe
import com.michel.weather.di.WeatherComponentHolder
import com.michel.weather.navigation.WeatherNavDirection
import com.michel.weather.presentation.composables.WeatherScreenContent
import com.michel.weather.presentation.mvi.entities.WeatherEffect
import com.michel.weather.presentation.mvi.entities.WeatherIntent
import com.michel.weather.presentation.mvi.entities.WeatherMessage
import com.michel.weather.presentation.mvi.entities.WeatherState
import javax.inject.Inject
import kotlin.reflect.KClass

val WeatherFragmentKClass: KClass<out Fragment> = WeatherFragment::class

class WeatherFragment : MviFragment<
        WeatherIntent,
        WeatherEffect,
        WeatherState,
        WeatherMessage,
        >() {

    @Inject
    lateinit var navApi: FeatureNavApi<WeatherNavDirection>

    @Inject
    override lateinit var viewModelFactory: StoreViewModelFactory<WeatherIntent, WeatherEffect, WeatherState, WeatherMessage>

    private val listeners: WeatherListeners by lazyUnsafe {
        WeatherListeners(
            onProfileClick = {
                viewModel.accept(WeatherIntent.ProfileClicked)
            },
            onSettingsClick = {
                viewModel.accept(WeatherIntent.SettingsClicked)
            },
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        WeatherComponentHolder
            .get()
            .inject(this)
    }

    override fun onDetach() {
        if (isRemoving) {
            WeatherComponentHolder.clear()
        }
        super.onDetach()
    }

    @Composable
    override fun Render(state: WeatherState) {
        WeatherScreenContent(
            state = state,
            listeners = listeners,
        )
    }

    override fun handleEffect(effect: WeatherEffect) {
        when (effect) {
            is WeatherEffect.Navigate -> navApi.navigate(effect.direction)
        }
    }
}
