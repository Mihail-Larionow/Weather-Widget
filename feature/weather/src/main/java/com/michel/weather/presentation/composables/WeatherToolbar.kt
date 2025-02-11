package com.michel.weather.presentation.composables

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.michel.designsystem.composables.buttons.icon.IconButton
import com.michel.designsystem.composables.buttons.icon.IconButtonSize
import com.michel.designsystem.composables.extensions.elevation
import com.michel.designsystem.composables.preview.ThemePreviews
import com.michel.designsystem.composables.toolbar.Toolbar
import com.michel.designsystem.theme.WeatherTheme

@Composable
internal fun WeatherToolbar(
    isListScrolled: State<Boolean>,
    onProfileClick: () -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val elevation by animateDpAsState(
        targetValue = if (isListScrolled.value) 4.dp else 0.dp,
        label = "elevation",
    )

    Toolbar(
        center = {
            Title()
        },
        left = {
            ProfileButton(
                onProfileClick = onProfileClick,
                modifier = Modifier.align(Alignment.CenterEnd),
            )
        },
        right = {
            SettingsButton(
                onSettingsClick = onSettingsClick,
                modifier = Modifier.align(Alignment.CenterStart),
            )
        },
        modifier = modifier
            .elevation(elevation)
            .fillMaxWidth()
            .background(WeatherTheme.colors.backgroundSecondary)
    )
}

@Composable
private fun Title(
    modifier: Modifier = Modifier,
) {
    Text(
        text = WeatherTheme.strings.weather.toolbarTitle,
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
private fun WeatherToolbarPreview() = WeatherTheme {
    val isListScrolled = remember { derivedStateOf { true } }

    WeatherToolbar(
        isListScrolled = isListScrolled,
        onProfileClick = { },
        onSettingsClick = { },
    )
}

