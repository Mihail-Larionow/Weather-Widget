package com.michel.deeplinks.di

import com.michel.api.FeatureNavApi
import com.michel.deeplinks.navigation.DeeplinkNavDirection
import com.michel.di.model.BaseDependencies

interface DeeplinkDependencies : BaseDependencies {

    val deeplinkFeatureNavApi: FeatureNavApi<DeeplinkNavDirection>
}
