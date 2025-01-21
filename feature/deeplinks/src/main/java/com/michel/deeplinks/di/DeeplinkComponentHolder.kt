package com.michel.deeplinks.di

import com.michel.di.holder.ComponentHolder

object DeeplinkComponentHolder : ComponentHolder<DeeplinkApi, DeeplinkDependencies>() {

    internal val internalApi: DeeplinkInternalApi
        get() = get() as DeeplinkInternalApi

    override fun build(
        dependencies: DeeplinkDependencies,
    ): DeeplinkApi = DaggerDeeplinkComponent.factory().create(dependencies)
}
