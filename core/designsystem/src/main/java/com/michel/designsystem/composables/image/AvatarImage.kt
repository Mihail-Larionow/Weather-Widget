package com.michel.designsystem.composables.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import com.michel.designsystem.theme.WeatherTheme

@Composable
fun AvatarImage(
    state: AvatarState,
    modifier: Modifier = Modifier,
) {
    var isPlaceholderShow by remember { mutableStateOf(true) }
    val avatarModifier = modifier
        .size(state.size)
        .clip(CircleShape)
    Placeholder(modifier = avatarModifier)
}

@Composable
private fun Placeholder(
    modifier: Modifier = Modifier,
    painter: Painter = WeatherTheme.icons.ic32.profilePlaceholder,
    tintColor: Color = WeatherTheme.colors.iconsPlaceholder,
) {
    Image(
        painter = painter,
        contentDescription = null,
        colorFilter = ColorFilter.tint(color = tintColor),
        modifier = modifier,
    )
}
