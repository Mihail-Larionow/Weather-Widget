package com.michel.weatherit.di.modules.feature

import com.michel.api.FeatureNavApi
import com.michel.navigation.presentation.navigation.NavApi
import com.michel.settings.di.SettingsDependencies
import com.michel.settings.navigation.SettingsNavDirection
import dagger.Module
import dagger.Provides

@Module
object SettingsModule {

    @Provides
    fun provideSettingsDependencies(
        navApi: NavApi,
    ): SettingsDependencies = object : SettingsDependencies {

        override val settingsFeatureNavApi: FeatureNavApi<SettingsNavDirection> =
            navApi.settingsNavApi
    }
}
