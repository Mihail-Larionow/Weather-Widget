package com.michel.profile.di

import kotlinx.coroutines.CoroutineScope

internal interface ProfileInternalApi {

    val coroutineScope: CoroutineScope
}
