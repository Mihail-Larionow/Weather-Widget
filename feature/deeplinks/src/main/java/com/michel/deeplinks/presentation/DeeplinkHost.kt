package com.michel.deeplinks.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.michel.deeplinks.di.DeeplinkComponentHolder
import com.michel.deeplinks.presentation.mvi.entities.DeeplinkEffect
import com.michel.deeplinks.presentation.mvi.entities.DeeplinkState

@Composable
fun DeeplinkHost() {
    DisposableEffect(DeeplinkComponentHolder) {
        DeeplinkComponentHolder.get()
        onDispose { DeeplinkComponentHolder.clear() }
    }

    val internalApi = remember { DeeplinkComponentHolder.internalApi }
    val viewModel = viewModel { internalApi.viewModelFactory.create() }

    LaunchedEffect(viewModel) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is DeeplinkEffect.Navigate -> internalApi.navApi.navigate(effect.direction)
            }
        }
    }

    DeeplinkHost(
        state = viewModel.state.collectAsState().value
    )
}

@Composable
private fun DeeplinkHost(
    state: DeeplinkState,
) {

}
