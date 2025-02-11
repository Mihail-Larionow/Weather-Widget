package com.michel.deeplinks.domain

import com.michel.deeplinks.domain.models.Deeplink
import com.michel.deeplinks.domain.models.DeeplinkAction
import kotlinx.coroutines.flow.Flow

interface DeeplinkHandler {

    val actions: Flow<DeeplinkAction?>

    suspend fun handle(deeplink: Deeplink)
}
