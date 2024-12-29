package com.michel.navigation.presentation.navgraph.destinations

import android.os.Parcelable
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.michel.feature.navigation.R
import com.michel.profile.presentation.ProfileFragmentKClass
import com.michel.settings.presentation.SettingsFragmentKClass
import com.michel.weather.presentation.WeatherFragmentKClass
import kotlin.reflect.KClass

abstract class FragmentDestination<ARGS : Parcelable?>(
    @IdRes override val id: Int,
    val fragmentType: KClass<out Fragment>,
) : OnScreenDestination<ARGS>, NavigableDestination<ARGS> {
    data object Weather : FragmentDestination<NoNavArgsStub>(
        id = R.id.weather_fragment,
        fragmentType = WeatherFragmentKClass,
    )

    data object Profile : FragmentDestination<NoNavArgsStub>(
        id = R.id.profile_fragment,
        fragmentType = ProfileFragmentKClass,
    )

    data object Settings : FragmentDestination<NoNavArgsStub>(
        id = R.id.settings_fragment,
        fragmentType = SettingsFragmentKClass,
    )
}
