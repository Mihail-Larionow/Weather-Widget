package com.michel.weatherit.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.michel.ui.component.WiBackground

@Composable
fun WeatherItApp(
    modifier: Modifier = Modifier,
    appState: WeatherAppState,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo()
) {
    WiBackground(modifier = modifier) {
        val snackbarHostState = remember { SnackbarHostState() }


    }
}

@Composable
internal fun WeatherItApp(
    modifier: Modifier = Modifier,
    appState: WeatherAppState,
) {

}