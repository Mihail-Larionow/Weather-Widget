package com.michel.profile.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.michel.profile.di.ProfileComponentHolder
import com.michel.profile.presentation.mvi.entities.ProfileEffect

@Composable
fun Profile() {
    DisposableEffect(ProfileComponentHolder) {
        ProfileComponentHolder.get()
        onDispose {
            ProfileComponentHolder.clear()
        }
    }

    val internalApi = remember { ProfileComponentHolder.internalApi }
    val viewModel = viewModel { internalApi.viewModelFactory.create() }

    LaunchedEffect(viewModel) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is ProfileEffect.Navigate -> internalApi.navApi.navigate(effect.direction)
            }
        }
    }

    ProfileScreen(
        state = viewModel.state.collectAsState().value,
        onIntent = viewModel::accept,
    )
}
