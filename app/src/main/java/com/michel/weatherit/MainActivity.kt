package com.michel.weatherit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import com.michel.ui.theme.WiTheme
import com.michel.weatherit.ui.WiApp
import com.michel.weatherit.ui.rememberWiAppState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val appState = rememberWiAppState()

            CompositionLocalProvider {
                WiTheme {
                    WiApp(appState = appState)
                }
            }
        }
    }
}