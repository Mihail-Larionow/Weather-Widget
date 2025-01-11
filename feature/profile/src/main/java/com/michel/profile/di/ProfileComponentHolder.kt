package com.michel.profile.di

import com.michel.di.holder.ComponentHolder

object ProfileComponentHolder : ComponentHolder<ProfileApi, ProfileDependencies>() {

    internal val internalApi: ProfileInternalApi
        get() = get() as ProfileInternalApi

    override fun build(dependencies: ProfileDependencies): ProfileApi =
        DaggerProfileComponent.factory().create(dependencies)
}
