package com.michel.settings.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.michel.designsystem.composables.buttons.icon.IconButton
import com.michel.designsystem.composables.buttons.icon.IconButtonSize
import com.michel.designsystem.composables.preview.ThemePreviews
import com.michel.designsystem.composables.toolbar.Toolbar
import com.michel.designsystem.theme.WeatherTheme

@Composable
internal fun SettingsToolbar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Toolbar(
        center = {
            Title(
                text = WeatherTheme.strings.settings.toolbarTitle,
            )
        },
        left = {
            BackButton(
                onBackClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterEnd),
            )
        },
        modifier = modifier.background(WeatherTheme.colors.backgroundPrimary),
    )
}

@Composable
private fun BackButton(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = onBackClick,
        backgroundColor = Color.Transparent,
        size = IconButtonSize.Large,
        modifier = modifier,
    ) {
        Icon(
            painter = WeatherTheme.icons.ic32.back,
            tint = WeatherTheme.colors.iconsPrimary,
            contentDescription = null,
        )
    }
}

@Composable
private fun Title(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        color = WeatherTheme.colors.textSecondary,
        style = WeatherTheme.typography.title1,
        modifier = modifier,
    )
}

@ThemePreviews
@Composable
private fun SettingsToolbarPreview() {
    WeatherTheme {
        SettingsToolbar(
            onBackClick = { },
        )
    }
}
