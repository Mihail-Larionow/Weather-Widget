package com.michel.navigation.di

import com.michel.di.holder.ComponentHolder
import com.michel.di.model.BaseApi
import com.michel.navigation.presentation.navigation.NavApi
import kotlin.reflect.KClass

object NavComponentHolder : ComponentHolder<NavApi, NavDependencies>() {

    override fun build(
        dependencies: NavDependencies
    ): NavApi = DaggerNavComponent.factory().create(dependencies)

    override fun buildSubcomponent(data: Any, type: KClass<*>): BaseApi = when (type) {
        NavPresentationSubcomponent::class -> {
            val component = get() as NavComponent
            component.getNavPresentationSubcomponent()
        }

        else -> super.buildSubcomponent(data, type)
    }
}
