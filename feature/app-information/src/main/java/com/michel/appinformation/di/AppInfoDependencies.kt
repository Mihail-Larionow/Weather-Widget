package com.michel.appinformation.di

import com.michel.api.FeatureNavApi
import com.michel.appinformation.navigation.AppInfoNavDirection
import com.michel.di.model.BaseDependencies

interface AppInfoDependencies : BaseDependencies {

    val appInfoFeatureNavApi: FeatureNavApi<AppInfoNavDirection>
}
