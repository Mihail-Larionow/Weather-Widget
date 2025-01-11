package com.michel.appinformation.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.michel.appinformation.di.AppInfoComponentHolder
import com.michel.appinformation.di.AppInfoInternalApi
import com.michel.appinformation.presentation.mvi.entities.AppInfoEffect
import com.michel.appinformation.presentation.mvi.entities.AppInfoIntent
import com.michel.appinformation.presentation.mvi.entities.AppInfoMessage
import com.michel.appinformation.presentation.mvi.entities.AppInfoState
import com.michel.mvi.store.StoreViewModel

@Composable
fun AppInfoScreen() {
    val internalApi = remember { AppInfoComponentHolder.internalApi }
    DisposableEffect(AppInfoComponentHolder) {
        AppInfoComponentHolder.get()
        onDispose {
            AppInfoComponentHolder.clear()
        }
    }
    AppInfoScreen(
        viewModel = viewModel<StoreViewModel<AppInfoIntent, AppInfoEffect, AppInfoState, AppInfoMessage>> {
            internalApi.viewModelFactory.create()
        },
        internalApi = internalApi,
    )
}

@Composable
private fun AppInfoScreen(
    viewModel: StoreViewModel<AppInfoIntent, AppInfoEffect, AppInfoState, AppInfoMessage>,
    internalApi: AppInfoInternalApi,
) {
    LaunchedEffect(viewModel) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is AppInfoEffect.Navigate -> internalApi.navApi.navigate(effect.direction)
            }
        }
    }
    AppInfoScreen(
        state = viewModel.state.collectAsState(),
        intentConsumer = viewModel::accept,
    )
}

@Composable
private fun AppInfoScreen(
    state: State<AppInfoState>,
    intentConsumer: (AppInfoIntent) -> Unit,
) {

}
