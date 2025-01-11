package com.michel.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.michel.designsystem.R.drawable as drawableR

class WeatherImages internal constructor() {
    val im24 = Im24()
}

class Im24 internal constructor() {
    val logo: Painter
        @Composable
        get() = painterResource(id = drawableR.icon_32_profile_placeholder)
}
