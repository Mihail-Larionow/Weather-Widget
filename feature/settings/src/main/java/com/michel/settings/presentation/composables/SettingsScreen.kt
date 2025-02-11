package com.michel.settings.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.michel.designsystem.composables.preview.ThemePreviews
import com.michel.designsystem.theme.WeatherTheme
import com.michel.settings.domain.model.SettingsItem
import com.michel.settings.domain.model.ToggleType
import com.michel.settings.presentation.mvi.entities.SettingsIntent
import com.michel.settings.presentation.mvi.entities.SettingsState

@Composable
internal fun SettingsScreen(
    state: SettingsState,
    onIntent: (SettingsIntent) -> Unit,
) {
    Scaffold(
        backgroundColor = WeatherTheme.colors.backgroundSecondary,
        topBar = {
            SettingsToolbar(
                onBackClick = { onIntent(SettingsIntent.BackClicked) },
            )
        },
    ) { paddingValues ->
        SettingsContent(
            state = state,
            onIntent = onIntent,
            paddingValues = paddingValues,
        )
    }
}

@Composable
private fun SettingsContent(
    state: SettingsState,
    onIntent: (SettingsIntent) -> Unit,
    paddingValues: PaddingValues,
) {
    when (state) {
        is SettingsState.Loading -> {}
        is SettingsState.Loaded -> LoadedContent(
            state = state,
            onIntent = onIntent,
            paddingValues = paddingValues,
        )
    }
}

@Composable
private fun LoadedContent(
    state: SettingsState.Loaded,
    onIntent: (SettingsIntent) -> Unit,
    paddingValues: PaddingValues,
) {
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .background(WeatherTheme.colors.backgroundSecondary)
    ) {
        itemsIndexed(state.items) { _, item ->
            when (item) {
                is SettingsItem.Toggle -> {

                }
            }
        }
    }
}


@ThemePreviews
@Composable
private fun LoadedSettingsScreenPreview() = WeatherTheme {
    SettingsScreen(
        state = SettingsState.Loaded(
            items = listOf(
                SettingsItem.Toggle(ToggleType.THEME, isEnabled = true),
            ),
        ),
        onIntent = { },
    )
}

