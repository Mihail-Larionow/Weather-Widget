package com.michel.appinformation.presentation.composables

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.michel.appinformation.di.AppInfoComponentHolder
import com.michel.appinformation.presentation.mvi.entities.AppInfoEffect
import com.michel.appinformation.presentation.mvi.entities.AppInfoIntent
import com.michel.appinformation.presentation.mvi.entities.AppInfoState
import com.michel.designsystem.theme.WeatherTheme

@Composable
fun AppInfo() {
    DisposableEffect(AppInfoComponentHolder) {
        AppInfoComponentHolder.get()
        onDispose {
            AppInfoComponentHolder.clear()
        }
    }

    val internalApi = remember { AppInfoComponentHolder.internalApi }
    val viewModel = viewModel { internalApi.viewModelFactory.create() }

    LaunchedEffect(viewModel) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is AppInfoEffect.Navigate -> internalApi.navApi.navigate(effect.direction)
            }
        }
    }

    AppInfoScreen(
        state = viewModel.state.collectAsState().value,
        onIntent = viewModel::accept,
    )
}
