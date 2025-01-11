package com.michel.weatherit.di.modules.feature

import com.michel.api.FeatureNavApi
import com.michel.appinformation.di.AppInfoDependencies
import com.michel.appinformation.navigation.AppInfoNavDirection
import com.michel.navigation.presentation.navigation.NavApi
import dagger.Module
import dagger.Provides

@Module
object AppInfoModule {

    @Provides
    fun provideAppInfoDependencies(
        navApi: NavApi,
    ): AppInfoDependencies = object : AppInfoDependencies {

        override val appInfoFeatureNavApi: FeatureNavApi<AppInfoNavDirection> = navApi.appInfoNavApi
    }
}
