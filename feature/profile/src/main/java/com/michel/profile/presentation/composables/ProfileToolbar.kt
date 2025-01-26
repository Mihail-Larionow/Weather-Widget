package com.michel.profile.presentation.composables

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
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
internal fun ProfileToolbar(
    isListScrolled: State<Boolean>,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val elevation by animateDpAsState(
        targetValue = if (isListScrolled.value) 4.dp else 0.dp,
        label = "elevation",
    )

    Toolbar(
        left = {
            BackButton(
                onBackClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterEnd),
            )
        },
        modifier = modifier
            .elevation(elevation)
            .fillMaxWidth()
            .background(WeatherTheme.colors.backgroundSecondary),
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
private fun ProfileToolbarInteractivePreview() = WeatherTheme {
    val isListScrolled = remember { derivedStateOf { true } }

    ProfileToolbar(
        isListScrolled = isListScrolled,
        onBackClick = { },
    )
}

