package com.michel.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.michel.designsystem.R.drawable as drawableR

class WeatherIcons internal constructor() {
    val ic32 = Ic32()

    val ic64 = Ic64()

    val ic128 = Ic128()
}

class Ic32 internal constructor() {

    val back: Painter
        @Composable
        get() = painterResource(id = drawableR.icon_32_back)

    val settings: Painter
        @Composable
        get() = painterResource(id = drawableR.icon_32_settings)

    val profilePlaceholder: Painter
        @Composable
        get() = painterResource(id = drawableR.icon_32_profile_placeholder)
}

class Ic64 internal constructor() {

    val sun: Painter
        @Composable
        get() = painterResource(id = drawableR.icon_64_sun)
}

class Ic128 internal constructor() {

    val sun: Painter
        @Composable
        get() = painterResource(id = drawableR.icon_128_sun)
}
