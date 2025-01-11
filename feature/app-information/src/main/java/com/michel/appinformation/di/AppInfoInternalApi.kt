package com.michel.appinformation.di

import com.michel.api.FeatureNavApi
import com.michel.appinformation.navigation.AppInfoNavDirection
import com.michel.appinformation.presentation.mvi.entities.AppInfoEffect
import com.michel.appinformation.presentation.mvi.entities.AppInfoIntent
import com.michel.appinformation.presentation.mvi.entities.AppInfoMessage
import com.michel.appinformation.presentation.mvi.entities.AppInfoState
import com.michel.mvi.store.StoreViewModelFactory

internal interface AppInfoInternalApi {

    val viewModelFactory: StoreViewModelFactory<AppInfoIntent, AppInfoEffect, AppInfoState, AppInfoMessage>

    val navApi: FeatureNavApi<AppInfoNavDirection>
}
