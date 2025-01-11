package com.michel.appinformation.di

import com.michel.di.holder.ComponentHolder

object AppInfoComponentHolder : ComponentHolder<AppInfoApi, AppInfoDependencies>() {

    internal val internalApi: AppInfoInternalApi
        get() = get() as AppInfoInternalApi

    override fun build(
        dependencies: AppInfoDependencies,
    ): AppInfoApi = DaggerAppInfoComponent.factory().create(dependencies)
}
