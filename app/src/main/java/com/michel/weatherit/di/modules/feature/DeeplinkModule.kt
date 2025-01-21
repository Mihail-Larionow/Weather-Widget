package com.michel.weatherit.di.modules.feature

import com.michel.api.FeatureNavApi
import com.michel.deeplinks.di.DeeplinkApi
import com.michel.deeplinks.di.DeeplinkComponentHolder
import com.michel.deeplinks.di.DeeplinkDependencies
import com.michel.deeplinks.domain.DeeplinkHandler
import com.michel.deeplinks.navigation.DeeplinkNavDirection
import com.michel.navigation.presentation.navigation.NavApi
import dagger.Module
import dagger.Provides

@Module
object DeeplinkModule {

    @Provides
    fun provideDeeplinkDependencies(
        navApi: NavApi,
    ): DeeplinkDependencies = object : DeeplinkDependencies {

        override val deeplinkFeatureNavApi: FeatureNavApi<DeeplinkNavDirection> =
            navApi.deeplinkNavApi
    }

    @Provides
    fun provideDeeplinkApi(): DeeplinkApi =
        DeeplinkComponentHolder.get()

    @Provides
    fun provideParseDeeplinkManager(
        deeplinkApi: DeeplinkApi
    ): DeeplinkHandler = deeplinkApi.deeplinkHandler
}
