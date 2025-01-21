package com.michel.mvi

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.michel.designsystem.theme.WeatherTheme
import com.michel.mvi.extensions.collectWhenStarted
import com.michel.mvi.store.StoreViewModel
import com.michel.mvi.store.StoreViewModelFactory
import com.michel.utils.ViewModelFactoryProvider
import dagger.android.support.DaggerAppCompatActivity


abstract class MviActivity<I, E, S, M> : DaggerAppCompatActivity() {

    abstract val viewModelFactory: StoreViewModelFactory<I, E, S, M>

    protected val viewModel: StoreViewModel<I, E, S, M> by viewModels {
        ViewModelFactoryProvider.provide(viewModelFactory::create)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        collectWhenStarted(viewModel, effectConsumer = ::handleEffect)
        setContent {
            val state by viewModel.state.collectAsState()
            CompositionLocalProvider {
                WeatherTheme {
                    Render(state)
                }
            }
        }
    }

    abstract fun handleEffect(effect: E)

    @Composable
    protected abstract fun Render(state: S)
}
