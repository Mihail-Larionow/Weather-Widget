package com.michel.designsystem.composables.buttons.icon

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.michel.designsystem.composables.preview.ThemePreviews
import com.michel.designsystem.theme.WeatherTheme

@Composable
fun IconButton(
    onClick: () -> Unit,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    disabledBackgroundColor: Color? = null,
    size: IconButtonSize = IconButtonSize.Medium,
    isEnabled: Boolean = true,
    content: @Composable () -> Unit,
) {
    Button(
        modifier = modifier.size(size.value),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            disabledBackgroundColor = disabledBackgroundColor ?: backgroundColor,
        ),
        shape = CircleShape,
        elevation = ButtonDefaults.noElevation(),
        contentPadding = buttonPadding,
        onClick = onClick,
        enabled = isEnabled,
    ) {
        content()
    }
}

@Composable
private fun ButtonDefaults.noElevation(): ButtonElevation =
    elevation(
        defaultElevation = 0.dp,
        pressedElevation = 0.dp,
        disabledElevation = 0.dp,
        hoveredElevation = 0.dp,
        focusedElevation = 0.dp,
    )

private val buttonPadding = PaddingValues(8.dp)

@ThemePreviews
@Composable
private fun IconButtonPreview() {
    WeatherTheme {
        IconButton(
            onClick = {},
            backgroundColor = WeatherTheme.colors.backgroundLine,
            content = {
                Icon(
                    painter = WeatherTheme.icons.ic32.settings,
                    tint = WeatherTheme.colors.iconsPrimary,
                    contentDescription = null,
                )
            },
        )
    }
}
