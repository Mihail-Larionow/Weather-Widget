package com.michel.appinformation.presentation.mvi

import com.michel.appinformation.navigation.AppInfoNavDirection
import com.michel.appinformation.presentation.mvi.entities.AppInfoIntent
import com.michel.appinformation.presentation.mvi.entities.AppInfoMessage
import com.michel.appinformation.presentation.mvi.entities.AppInfoState
import com.michel.mvi.store.Actor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class AppInfoActor @Inject constructor() : Actor<AppInfoIntent, AppInfoState, AppInfoMessage> {
    override fun run(intent: AppInfoIntent, prevState: AppInfoState): Flow<AppInfoMessage> =
        when (intent) {
            is AppInfoIntent.BackClicked -> flowOf(AppInfoMessage.Navigate(AppInfoNavDirection.Up))
        }
}
