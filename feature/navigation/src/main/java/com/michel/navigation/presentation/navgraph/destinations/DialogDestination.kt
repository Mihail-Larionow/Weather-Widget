package com.michel.navigation.presentation.navgraph.destinations

import android.os.Parcelable
import androidx.annotation.IdRes
import androidx.fragment.app.DialogFragment
import kotlin.reflect.KClass

abstract class DialogDestination<ARGS : Parcelable?>(
    @IdRes override val id: Int,
    val fragmentType: KClass<out DialogFragment>,
) : OnScreenDestination<ARGS>, NavigableDestination<ARGS> {

}
