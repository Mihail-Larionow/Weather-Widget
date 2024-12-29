package com.michel.mvi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.michel.mvi.extensions.collectWhenStarted
import com.michel.mvi.store.StoreViewModel
import com.michel.mvi.store.StoreViewModelFactory
import com.michel.ui.theme.WeatherTheme
import com.michel.utils.ViewModelFactoryProvider

abstract class MviFragment<I, E, S, M> : Fragment() {

    protected abstract val viewModelFactory: StoreViewModelFactory<I, E, S, M>

    protected val viewModel: StoreViewModel<I, E, S, M> by viewModels {
        ViewModelFactoryProvider.provide(viewModelFactory::create)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val state by viewModel.state.collectAsState()
                WeatherTheme {
                    Render(state = state)
                }
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectWhenStarted(viewModel, effectConsumer = ::handleEffect)
    }

    protected abstract fun handleEffect(effect: E)

    @Composable
    protected abstract fun Render(state: S)
}
