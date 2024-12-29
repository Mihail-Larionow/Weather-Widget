package com.michel.mvi

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.michel.mvi.extensions.collectWhenStarted
import com.michel.mvi.store.StoreViewModel
import com.michel.mvi.store.StoreViewModelFactory
import com.michel.utils.ViewModelFactoryProvider
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.launch


abstract class MviActivity<I, E, S, M> : DaggerAppCompatActivity() {

    abstract val viewModelFactory: StoreViewModelFactory<I, E, S, M>

    protected val viewModel: StoreViewModel<I, E, S, M> by viewModels {
        ViewModelFactoryProvider.provide(viewModelFactory::create)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        collectWhenStarted(viewModel, effectConsumer = ::handleEffect)

        lifecycleScope.launch {
            viewModel.state.collect {
                render(it)
            }
        }
    }

    abstract fun handleEffect(effect: E)

    protected abstract fun render(state: S)
}
