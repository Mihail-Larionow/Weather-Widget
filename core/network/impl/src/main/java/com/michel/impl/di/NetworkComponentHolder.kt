package com.michel.impl.di

import com.michel.di.holder.ComponentHolder

object NetworkComponentHolder : ComponentHolder<NetworkApi, NetworkDependencies>() {

    override fun build(dependencies: NetworkDependencies): NetworkApi =
        DaggerNetworkComponent.factory().create(dependencies)
}
