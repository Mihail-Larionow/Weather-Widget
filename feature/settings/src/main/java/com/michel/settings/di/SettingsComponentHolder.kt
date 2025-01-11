package com.michel.settings.di

import com.michel.di.holder.ComponentHolder

object SettingsComponentHolder : ComponentHolder<
        SettingsApi,
        SettingsDependencies,
        >() {

    internal val internalApi: SettingsInternalApi
        get() = get() as SettingsInternalApi

    override fun build(dependencies: SettingsDependencies): SettingsApi =
        DaggerSettingsComponent.factory().create(dependencies)
}
