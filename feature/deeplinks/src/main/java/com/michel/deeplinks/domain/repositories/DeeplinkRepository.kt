package com.michel.deeplinks.domain.repositories

import com.michel.deeplinks.domain.models.Deeplink
import com.michel.deeplinks.domain.models.DeeplinkAction

interface DeeplinkRepository {

    fun parse(deeplink: Deeplink): Result<DeeplinkAction>
}
