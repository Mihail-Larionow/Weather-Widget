package com.michel.designsystem.composables.toolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.michel.designsystem.composables.extensions.clickableWithRipple
import com.michel.designsystem.theme.WeatherTheme

private val IconSlotWidth = 60.dp

@Composable
fun Toolbar(
    title: String,
    leftIcon: Painter,
    rightIcon: Painter,
    modifier: Modifier = Modifier,
    onLeftIconClick: () -> Unit,
    onRightIconClick: () -> Unit,
) {
    Toolbar(
        modifier = modifier.background(color = WeatherTheme.colors.backgroundSecondary),
        left = {
            ToolbarIcon(painter = leftIcon, onClick = onLeftIconClick)
        },
        center = {
            Text(
                text = title,
                style = WeatherTheme.typography.title3,
                color = WeatherTheme.colors.textPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        right = {
            ToolbarIcon(painter = rightIcon, onClick = onRightIconClick)
        },
    )
}

@Composable
private fun ToolbarIcon(painter: Painter, onClick: () -> Unit) {
    Icon(
        painter = painter,
        contentDescription = null,
        tint = WeatherTheme.colors.iconsPrimary,
        modifier = Modifier
            .clickableWithRipple(shape = CircleShape, onClick = onClick)
            .padding(12.dp),
    )

}

@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    left: (@Composable BoxScope.() -> Unit)? = null,
    center: (@Composable () -> Unit)? = null,
    right: (@Composable BoxScope.() -> Unit)? = null,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterHorizontally),
        modifier = modifier
            .statusBarsPadding()
            .height(ToolbarDefaults.height),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(width = IconSlotWidth, height = ToolbarDefaults.height),
        ) {
            left?.invoke(this)
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.weight(1f),
        ) {
            center?.invoke()
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(width = IconSlotWidth, height = ToolbarDefaults.height),
        ) {
            right?.invoke(this)
        }
    }
}
