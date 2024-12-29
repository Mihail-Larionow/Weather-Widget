package com.michel.mvi

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.michel.mvi.extensions.collectWhenStarted
import com.michel.mvi.store.StoreViewModel
import com.michel.mvi.store.StoreViewModelFactory
import com.michel.utils.ViewModelFactoryProvider
import kotlinx.coroutines.launch

abstract class MviNavFragment<I, E, S, M>(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    protected abstract val viewModelFactory: StoreViewModelFactory<I, E, S, M>

    protected val viewModel: StoreViewModel<I, E, S, M> by viewModels {
        ViewModelFactoryProvider.provide(viewModelFactory::create)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectWhenStarted(viewModel, effectConsumer = ::handleEffect)

        lifecycleScope.launch {
            viewModel.state.collect {
                render(it)
            }
        }
    }

    abstract fun handleEffect(effect: E)

    abstract fun render(state: S)
}
