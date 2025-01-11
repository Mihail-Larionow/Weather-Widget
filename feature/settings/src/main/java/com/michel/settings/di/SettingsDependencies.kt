package com.michel.settings.di

import com.michel.api.FeatureNavApi
import com.michel.di.model.BaseDependencies
import com.michel.settings.navigation.SettingsNavDirection

interface SettingsDependencies : BaseDependencies {

    val settingsFeatureNavApi: FeatureNavApi<SettingsNavDirection>
}
