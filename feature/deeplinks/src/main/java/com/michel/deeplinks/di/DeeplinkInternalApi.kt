package com.michel.deeplinks.di

import com.michel.api.FeatureNavApi
import com.michel.deeplinks.navigation.DeeplinkNavDirection
import com.michel.deeplinks.presentation.DeeplinkViewModelFactory

internal interface DeeplinkInternalApi {

    val viewModelFactory: DeeplinkViewModelFactory

    val navApi: FeatureNavApi<DeeplinkNavDirection>
}
