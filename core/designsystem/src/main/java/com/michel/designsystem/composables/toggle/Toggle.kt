package com.michel.designsystem.composables.toggle

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.michel.designsystem.theme.WeatherTheme

@Composable
fun Toggle(
    checked: Boolean,
    onChecked: (Boolean) -> Unit,
    width: Dp = 60.dp,
    height: Dp = 40.dp,
    colors: DefaultTravelSwitchColor = DefaultTravelSwitchColor(
        checkedTrackColor = WeatherTheme.colors.controlsPrimary,
        uncheckedTrackColor = WeatherTheme.colors.switchTrackUnchecked,
        checkedThumbColor = WeatherTheme.colors.switchThumb,
        uncheckedThumbColor = WeatherTheme.colors.switchThumb,
        shadowColor = WeatherTheme.colors.switchThumbShadow,
    ),
) {
    val gapBetweenThumbAndTrackEdge = 2.dp
    val thumbRadius = (height / 2) - gapBetweenThumbAndTrackEdge

    val animatePosition = animateFloatAsState(
        targetValue = if (checked) {
            with(LocalDensity.current) { (width - thumbRadius - gapBetweenThumbAndTrackEdge).toPx() }
        } else {
            with(LocalDensity.current) { (thumbRadius + gapBetweenThumbAndTrackEdge).toPx() }
        },
        label = "indicator position",
    )

    Canvas(
        modifier = Modifier
            .size(width = width, height = height)
            .toggleable(
                value = checked,
                role = Role.Switch,
                onValueChange = onChecked,
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
            )
    ) {
        drawBackground(checked, colors)
        val semiSize = thumbRadius.toPx()
        val summaryGap = (gapBetweenThumbAndTrackEdge * 2).toPx()

        drawOutline(animatePosition, semiSize, summaryGap, thumbRadius, colors)
        drawIndicator(checked, colors, thumbRadius, animatePosition)
    }
}

private fun DrawScope.drawBackground(
    checked: Boolean,
    colors: DefaultTravelSwitchColor,
) {
    drawRoundRect(
        color = if (checked) {
            colors.checkedTrackColor
        } else {
            colors.uncheckedTrackColor
        },
        cornerRadius = CornerRadius(x = 100.dp.toPx(), y = 100.dp.toPx())
    )
}

private fun DrawScope.drawIndicator(
    checked: Boolean,
    colors: DefaultTravelSwitchColor,
    thumbRadius: Dp,
    animatePosition: State<Float>,
) {
    drawCircle(
        color = if (checked) {
            colors.checkedThumbColor
        } else {
            colors.uncheckedThumbColor
        },
        radius = thumbRadius.toPx(),
        center = Offset(
            x = animatePosition.value,
            y = size.height / 2
        ),
    )
}

private fun DrawScope.drawOutline(
    animatePosition: State<Float>,
    semiSize: Float,
    summaryGap: Float,
    thumbRadius: Dp,
    colors: DefaultTravelSwitchColor,
) {
    drawOutline(
        outline = Outline.Rounded(
            roundRect = RoundRect(
                Rect(
                    topLeft = Offset(
                        x = animatePosition.value - semiSize + summaryGap / 2,
                        y = size.height / 2 - semiSize + summaryGap / 2,
                    ),
                    bottomRight = Offset(
                        x = animatePosition.value + semiSize - summaryGap / 2,
                        y = size.height / 2 + semiSize,
                    ),
                ),
                cornerRadius = CornerRadius(x = thumbRadius.toPx(), y = thumbRadius.toPx())
            )
        ),
        color = colors.shadowColor,
        style = Stroke(width = summaryGap),
    )
}

data class DefaultTravelSwitchColor(
    val checkedThumbColor: Color,
    val checkedTrackColor: Color,
    val uncheckedThumbColor: Color,
    val uncheckedTrackColor: Color,
    val shadowColor: Color,
)

@Preview(name = "Light theme checked")
@Preview(name = "Dark theme checked", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewCheckedTravelSwitch() {
    WeatherTheme {
        Toggle(checked = true, onChecked = {})
    }
}

@Preview(name = "Light theme unchecked")
@Preview(name = "Dark theme unchecked", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewUncheckedTravelSwitch() {
    WeatherTheme {
        Toggle(checked = false, onChecked = {})
    }
}
