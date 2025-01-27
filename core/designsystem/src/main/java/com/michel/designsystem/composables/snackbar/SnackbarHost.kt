package com.michel.designsystem.composables.snackbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SnackbarHost(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val snackbarState = Snackbar.stateFlow.collectAsState().value

    Box(modifier = modifier) {
        content()
        SnackbarHost(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 64.dp),
            hostState = snackbarHostState,
            snackbar = {
                Snackbar(
                    state = snackbarState,
                    modifier = Modifier.padding(8.dp),
                )
            }
        )
    }

    LaunchedEffect(key1 = snackbarState) {
        if (snackbarState is SnackbarState.Shown) {
            snackbarHostState.showSnackbar(
                message = "",
                duration = snackbarState.duration,
            )
            Snackbar.hide()
        }
    }
}

@Composable
private fun Snackbar(
    state: SnackbarState,
    modifier: Modifier = Modifier,
) {
    when (state) {
        is SnackbarState.Hidden -> {}
        is SnackbarState.Shown -> Snackbar(state.layout, modifier)
    }
}

@Composable
private fun Snackbar(
    layout: SnackbarLayout,
    modifier: Modifier = Modifier,
) {
    when (layout) {
        is SnackbarLayout.Toast -> Toast(
            text = layout.text,
        )

        is SnackbarLayout.WithActionButton -> Snackbar(
            text = layout.text,
            icon = layout.icon,
            buttonTitle = layout.buttonTitle,
            onButtonClick = layout.onButtonClick,
            modifier = modifier,
        )
    }
}
