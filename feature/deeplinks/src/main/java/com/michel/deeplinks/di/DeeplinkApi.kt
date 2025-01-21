package com.michel.deeplinks.di

import com.michel.deeplinks.domain.DeeplinkHandler
import com.michel.di.model.BaseApi

interface DeeplinkApi : BaseApi {

    val deeplinkHandler: DeeplinkHandler
}
