package com.michel.designsystem.composables

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.michel.designsystem.composables.preview.ThemePreviews
import com.michel.designsystem.theme.WeatherTheme

private const val SHIMMER_ALPHA_FROM = 1f
private const val SHIMMER_ALPHA_TO = 0.6f

@Composable
fun Skeleton(
    modifier: Modifier = Modifier,
    color: Color = WeatherTheme.colors.backgroundSkeleton,
    shape: Shape = RectangleShape,
    isShimmerEnabled: Boolean = true,
) {
    Box(
        modifier = modifier
            .skeletonBackground(
                shape = shape,
                color = color,
                isShimmerEnabled = isShimmerEnabled,
            ),
    )
}

@Composable
private fun Modifier.skeletonBackground(
    shape: Shape,
    color: Color,
    isShimmerEnabled: Boolean,
): Modifier {
    val shimmerEffect = if (isShimmerEnabled) {
        val transition = rememberInfiniteTransition(label = "shimmer infinite transition")
        val translateAnimation by transition.animateFloat(
            initialValue = SHIMMER_ALPHA_FROM,
            targetValue = SHIMMER_ALPHA_TO,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = WeatherTheme.integers.durationM,
                    easing = LinearOutSlowInEasing,
                ),
                repeatMode = RepeatMode.Reverse,
            ),
            label = "skeleton_background",
        )
        alpha(translateAnimation)
    } else {
        null
    }

    return (shimmerEffect ?: Modifier).background(
        color = color,
        shape = shape,
    )
}

@ThemePreviews
@Composable
private fun Preview() {
    WeatherTheme {
        Skeleton(
            modifier = Modifier.size(
                width = 150.dp,
                height = 180.dp,
            ),
            shape = RoundedCornerShape(16.dp),
            isShimmerEnabled = true,
        )
    }
}
