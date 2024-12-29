package com.michel.navigation.presentation.navcontroller

import androidx.annotation.AnimRes
import androidx.annotation.AnimatorRes
import androidx.annotation.IdRes
import com.michel.feature.navigation.R

data class WeatherNavOptions(
    val singleTop: Boolean? = null,
    val restoreState: Boolean? = null,
    val popUpOptions: PopUp? = null,
    val animations: Animations? = Animations(
        enter = R.anim.weather_feature_nav_slide_in_right,
        exit = R.anim.weather_feature_nav_wait_anim,
        popEnter = R.anim.weather_feature_nav_wait_anim,
        popExit = R.anim.weather_feature_nav_slide_out_right,
    ),
) {

    data class PopUp(
        @IdRes val destinationId: Int,
        val inclusive: Boolean,
        val saveState: Boolean = false,
    )

    data class Animations(
        @AnimatorRes @AnimRes val enter: Int,
        @AnimatorRes @AnimRes val exit: Int,
        @AnimatorRes @AnimRes val popEnter: Int,
        @AnimatorRes @AnimRes val popExit: Int,
    )
}
