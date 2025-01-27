package com.michel.designsystem.composables.snackbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.michel.designsystem.composables.extensions.clickableWithRipple
import com.michel.designsystem.composables.preview.ThemePreviews
import com.michel.designsystem.theme.WeatherTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object Snackbar {
    private val mutableStateFlow = MutableStateFlow<SnackbarState>(SnackbarState.Hidden)
    internal val stateFlow get() = mutableStateFlow.asStateFlow()

    fun show(
        layout: SnackbarLayout,
        duration: SnackbarDuration,
    ) {
        mutableStateFlow.value = SnackbarState.Shown(
            layout = layout,
            duration = duration,
        )
    }

    fun hide() {
        mutableStateFlow.value = SnackbarState.Hidden
    }
}

@Composable
internal fun Snackbar(
    text: String,
    icon: Painter,
    buttonTitle: String,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .background(
                color = WeatherTheme.colors.backgroundLine,
                shape = RoundedCornerShape(16.dp),
            )
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.Top,
            modifier = Modifier.weight(1f),
        ) {
            SnackbarIcon(
                icon = icon,
                modifier = Modifier.padding(top = 2.dp)
            )
            Title(
                text = text,
                maxLines = 2,
            )
        }
        Button(
            title = buttonTitle,
            onClick = onButtonClick,
        )
    }
}

@Composable
internal fun Toast(
    text: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .background(
                color = WeatherTheme.colors.backgroundLine,
                shape = RoundedCornerShape(16.dp),
            )
            .padding(16.dp)
    ) {
        Title(
            text = text,
            maxLines = 1,
        )
    }
}

@Composable
private fun Title(
    text: String,
    maxLines: Int,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = WeatherTheme.typography.body3,
        color = WeatherTheme.colors.textPrimary,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier,
    )
}

@Composable
private fun SnackbarIcon(
    icon: Painter,
    modifier: Modifier = Modifier,
) {
    Icon(
        painter = icon,
        tint = WeatherTheme.colors.iconsPrimary,
        contentDescription = null,
        modifier = modifier,
    )
}

@Composable
private fun Button(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .wrapContentWidth()
            .clickableWithRipple { onClick() }
            .clip(RoundedCornerShape(8.dp))
            .background(WeatherTheme.colors.controlsPrimary)
            .padding(12.dp)
    ) {
        Text(
            text = title,
            style = WeatherTheme.typography.body3,
        )
    }
}


@ThemePreviews
@Composable
private fun SnackbarPreview() = WeatherTheme {
    Snackbar(
        text = "Это превью снекбар",
        icon = WeatherTheme.icons.ic32.settings,
        buttonTitle = "Кнопка",
        onButtonClick = { }
    )
}

@ThemePreviews
@Composable
private fun ToastPreview() = WeatherTheme {
    Toast(
        text = "Это превью тоста",
    )
}
