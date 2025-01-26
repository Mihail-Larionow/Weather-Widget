package com.michel.appinformation.presentation.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.michel.appinformation.presentation.mvi.entities.AppInfoIntent
import com.michel.appinformation.presentation.mvi.entities.AppInfoState
import com.michel.designsystem.composables.preview.ThemePreviews
import com.michel.designsystem.theme.WeatherTheme

@Composable
internal fun AppInfoScreen(
    state: AppInfoState,
    onIntent: (AppInfoIntent) -> Unit,
) {
    Scaffold(
        backgroundColor = WeatherTheme.colors.backgroundSecondary,
        topBar = {
            AppInfoToolbar()
        }
    ) { innerPadding ->
        AppInfoContent(
            state = state,
            onIntent = onIntent,
            paddingValues = innerPadding,
        )
    }
}

@Composable
private fun AppInfoContent(
    state: AppInfoState,
    onIntent: (AppInfoIntent) -> Unit,
    paddingValues: PaddingValues,
) {
    when (state) {
        is AppInfoState.Loading -> LoadedContent(
            state = state,
            onIntent = onIntent,
            paddingValues = paddingValues,
        )
    }
}

@Composable
private fun LoadedContent(
    state: AppInfoState,
    onIntent: (AppInfoIntent) -> Unit,
    paddingValues: PaddingValues,
) {

}

@Composable
private fun AppInfoScreenPreview() = WeatherTheme {
    AppInfoScreen(
        state = AppInfoState.Loading,
        onIntent = { },
    )
}
