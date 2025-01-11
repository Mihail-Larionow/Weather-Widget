package com.michel.settings.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.michel.designsystem.theme.WeatherTheme
import com.michel.settings.domain.model.SettingsItem
import com.michel.settings.domain.model.ToggleType
import com.michel.settings.presentation.mvi.entities.SettingsIntent
import com.michel.settings.presentation.mvi.entities.SettingsState

@Composable
fun SettingsScreenContent(
    state: SettingsState,
    onIntent: (SettingsIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            SettingsToolbar(
                onBackClick = { onIntent(SettingsIntent.BackClicked) },
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .padding(paddingValues)
                .background(WeatherTheme.colors.backgroundSecondary)
        ) {
            itemsIndexed(state.items) { _, item ->
                when (item) {
                    is SettingsItem.Toggle -> {
                        val (title, subtitle) = item.getToggleText()
                        ToggleItem(
                            title = title,
                            subtitle = subtitle,
                            item = item,
                            onCheckedChange = { isChecked ->
                                onIntent(
                                    SettingsIntent.ToggleStateUpdated(
                                        SettingsItem.Toggle(
                                            toggleType = item.toggleType,
                                            isEnabled = isChecked,
                                        )
                                    )
                                )
                            },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ToggleItem(
    title: String,
    subtitle: String,
    item: SettingsItem.Toggle,
    onCheckedChange: (isChecked: Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {

}

@Composable
private fun SettingsItem.Toggle.getToggleText(): ToggleText = when (toggleType) {
    ToggleType.THEME -> ToggleText(
        title = WeatherTheme.strings.settings.themeToggleTitle,
        subtitle = WeatherTheme.strings.settings.themeToggleSubtitle,
    )
}

private data class ToggleText(
    val title: String,
    val subtitle: String,
)

internal val MockWeatherState = SettingsState(
    items = listOf(
        SettingsItem.Toggle(ToggleType.THEME, isEnabled = true)
    ),
)

@com.michel.designsystem.composables.preview.ThemePreviews
@Composable
private fun SettingsScreenPreview() {
    SettingsScreenContent(
        state = MockWeatherState,
        onIntent = { },
        modifier = Modifier.fillMaxSize(),
    )
}
