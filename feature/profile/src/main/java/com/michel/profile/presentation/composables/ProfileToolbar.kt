package com.michel.profile.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.material.Icon
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
internal fun ProfileToolbar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Toolbar(
        left = {
            BackButton(
                onBackClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterEnd),
            )
        },
        modifier = modifier.background(WeatherTheme.colors.backgroundSecondary),
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

@ThemePreviews
@Composable
private fun WeatherToolbarPreview() {
    WeatherTheme {
        ProfileToolbar(
            onBackClick = { },
        )
    }
}
