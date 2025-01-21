package com.michel.profile.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.michel.mvi.store.StoreViewModel
import com.michel.profile.di.ProfileComponentHolder
import com.michel.profile.di.ProfileInternalApi
import com.michel.profile.presentation.composables.ProfileScreenContent
import com.michel.profile.presentation.mvi.entities.ProfileEffect
import com.michel.profile.presentation.mvi.entities.ProfileIntent
import com.michel.profile.presentation.mvi.entities.ProfileMessage
import com.michel.profile.presentation.mvi.entities.ProfileState

@Composable
fun ProfileScreen() {
    val internalApi = remember { ProfileComponentHolder.internalApi }
    DisposableEffect(ProfileComponentHolder) {
        ProfileComponentHolder.get()
        onDispose {
            ProfileComponentHolder.clear()
        }
    }
    ProfileScreen(
        viewModel = viewModel<StoreViewModel<ProfileIntent, ProfileEffect, ProfileState, ProfileMessage>> {
            internalApi.viewModelFactory.create()
        },
        internalApi = internalApi,
    )
}

@Composable
private fun ProfileScreen(
    viewModel: StoreViewModel<ProfileIntent, ProfileEffect, ProfileState, ProfileMessage>,
    internalApi: ProfileInternalApi,
) {
    LaunchedEffect(viewModel) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is ProfileEffect.Navigate -> internalApi.navApi.navigate(effect.direction)
            }
        }
    }
    ProfileScreen(
        state = viewModel.state.collectAsState(),
        intentConsumer = viewModel::accept,
    )
}

@Composable
private fun ProfileScreen(
    state: State<ProfileState>,
    intentConsumer: (ProfileIntent) -> Unit,
) {
    ProfileScreenContent(
        state = state.value,
        onIntent = intentConsumer,
    )
}
