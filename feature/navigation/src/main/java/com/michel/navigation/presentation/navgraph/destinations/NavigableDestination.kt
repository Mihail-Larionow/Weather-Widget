package com.michel.navigation.presentation.navgraph.destinations

import android.os.Parcelable

sealed interface NavigableDestination<ARGS : Parcelable?> {
    val id: Int
}

interface OnScreenDestination<ARGS : Parcelable?> {
    val id: Int
}
