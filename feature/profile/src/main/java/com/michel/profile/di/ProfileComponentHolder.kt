package com.michel.profile.di

import com.michel.di.holder.ComponentHolder
import kotlinx.coroutines.cancel

object ProfileComponentHolder : ComponentHolder<
        ProfileApi,
        ProfileDependencies,
        >() {

    private fun getInternalApi(): ProfileInternalApi =
        get() as ProfileInternalApi

    override fun build(
        dependencies: ProfileDependencies,
    ): ProfileApi = DaggerProfileComponent.factory().create(dependencies)

    override fun beforeClear() {
        getInternalApi().coroutineScope.cancel()
    }
}
