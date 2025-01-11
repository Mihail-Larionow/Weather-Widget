package com.michel.weather.presentation.composables

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
internal fun WeatherToolbar(
    onProfileClick: () -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Toolbar(
        center = {
            Title(
                text = WeatherTheme.strings.weather.toolbarTitle,
            )
        },
        left = {
            ProfileButton(
                onProfileClick = onProfileClick,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        },
        right = {
            SettingsButton(
                onSettingsClick = onSettingsClick,
                modifier = Modifier.align(Alignment.CenterStart)
            )
        },
        modifier = modifier.background(WeatherTheme.colors.backgroundPrimary),
    )
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

@Composable
private fun SettingsButton(
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = onSettingsClick,
        backgroundColor = Color.Transparent,
        size = IconButtonSize.Large,
        modifier = modifier,
    ) {
        Icon(
            painter = WeatherTheme.icons.ic32.settings,
            tint = WeatherTheme.colors.iconsSecondary,
            contentDescription = null,
        )
    }
}

@Composable
private fun ProfileButton(
    onProfileClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = onProfileClick,
        backgroundColor = Color.Transparent,
        size = IconButtonSize.Large,
        modifier = modifier,
    ) {
        Icon(
            painter = WeatherTheme.icons.ic32.profilePlaceholder,
            tint = WeatherTheme.colors.iconsSecondary,
            contentDescription = null,
        )
    }
}

@ThemePreviews
@Composable
private fun WeatherToolbarPreview() {
    WeatherTheme {
        WeatherToolbar(
            onProfileClick = { },
            onSettingsClick = { },
        )
    }
}
