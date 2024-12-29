package com.michel.ui.composables.bottomsheet

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.michel.ui.composables.extansions.bottomInsetsPadding
import com.michel.ui.theme.WeatherTheme

private val FullscreenBottomSheetScrimColor = Color.Black
private val PartialBottomSheetScrimColor = Color.Black.copy(alpha = 0.2f)

// Reduce composable usage scope
@Composable
fun WeatherModalBottomSheet(
    sheetState: ModalBottomSheetState,
    sheetCornersStyle: BottomSheetCornerStyle,
    onDismiss: () -> Unit,
    hideBottomSheetTrigger: suspend () -> Unit,
    isFullscreen: Boolean = false,
    content: @Composable () -> Unit,
) {
    val bottomSheetFullyOpened by remember {
        derivedStateOf { sheetState.targetValue == ModalBottomSheetValue.Expanded }
    }

    val animatedColor by animateColorAsState(
        if (bottomSheetFullyOpened) FullscreenBottomSheetScrimColor else Color.Transparent,
        label = "bottom sheet background color"
    )

    Box(
        modifier = Modifier
            .drawBehind {
                drawRect(
                    if (isFullscreen) {
                        animatedColor
                    } else {
                        PartialBottomSheetScrimColor
                    }
                )
            }
            .statusBarsPadding()
            .navigationBarsPadding(),
    ) {
        ModalBottomSheetLayout(
            sheetState = sheetState,
            sheetShape = createBottomSheetShape(sheetCornersStyle),
            sheetElevation = 0.dp,
            scrimColor = Color.Transparent,
            sheetBackgroundColor = WeatherTheme.colors.backgroundSecondary,
            content = {
                Box(modifier = Modifier.fillMaxSize())
            },
            sheetContent = {
                val contentModifier = remember(isFullscreen) {
                    if (isFullscreen) {
                        Modifier.fillMaxSize()
                    } else {
                        Modifier
                    }
                }
                Box(
                    modifier = contentModifier
                        .bottomInsetsPadding(),
                ) {
                    Spacer(modifier = Modifier.height(0.5.dp)) // Avoid an app crash for the empty content
                    content()
                }
            },
        )
    }

    HandleDismiss(
        sheetState = sheetState,
        hideBottomSheetTrigger = hideBottomSheetTrigger,
        onDismiss = onDismiss,
    )
    LaunchedEffect(key1 = Unit) {
        sheetState.show()
    }
}

@Composable
private fun HandleDismiss(
    sheetState: ModalBottomSheetState,
    hideBottomSheetTrigger: suspend () -> Unit,
    onDismiss: () -> Unit,
) {
    var isShown by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = Unit) {
        hideBottomSheetTrigger()
        try {
            sheetState.hide()
        } finally {
            onDismiss()
        }
    }
    LaunchedEffect(key1 = sheetState.currentValue, key2 = sheetState.targetValue) {
        when {
            !isShown && sheetState.targetValue != ModalBottomSheetValue.Hidden -> isShown = true
            isShown && !sheetState.isVisible -> onDismiss()
        }
    }
}

private fun createBottomSheetShape(style: BottomSheetCornerStyle): RoundedCornerShape =
    RoundedCornerShape(
        topStart = style.topRadius,
        topEnd = style.topRadius,
        bottomStart = style.bottomRadius,
        bottomEnd = style.bottomRadius,
    )
