package com.michel.di.holder

import com.michel.di.model.BaseApi
import com.michel.di.model.BaseDependencies
import com.michel.di.model.SubcomponentKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import javax.inject.Provider
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.reflect.KClass

abstract class ComponentHolder<
    C : BaseApi,
    D : BaseDependencies
> : BaseComponentHolder<D>() {

    protected lateinit var dependenciesProvider: Provider<D>

    private var component: C? = null
    private var innerCoroutineScope: CoroutineScope? = null

    private val subcomponents: MutableMap<SubcomponentKey, BaseApi> = mutableMapOf()

    protected abstract fun build(dependencies: D): C

    fun init(provider: Provider<D>) {
        if (::dependenciesProvider.isInitialized) {
            error("You must init Component Holder only once")
        }

        dependenciesProvider = provider
        onAfterInit()
    }

    fun get(): C = component ?: build(dependenciesProvider.get())
        .also { newComponent -> component = newComponent }

    fun componentScope(
        coroutineContext: CoroutineContext = EmptyCoroutineContext,
    ): CoroutineScope {
        return innerCoroutineScope ?: CoroutineScope(componentScopeContext + coroutineContext)
            .also { innerCoroutineScope = it }
    }

    fun clear() {
        beforeClear()
        subcomponents.clear()
        innerCoroutineScope?.cancel()
        innerCoroutineScope = null
        component = null
    }

    protected open fun beforeClear() {
        // Override if necessary
    }

    open fun buildSubcomponent(data: Any, type: KClass<*>): BaseApi {
        throw IllegalArgumentException("Method 'buildSubcomponent' is not implemented for types provided " +
                "(data: $data, type: ${type.qualifiedName})")
    }

    @Suppress("UNCHECKED_CAST")
    fun <SUBCOMPONENT : BaseApi> getSubcomponent(clazz: KClass<SUBCOMPONENT>, data: Any): SUBCOMPONENT {
        val key = SubcomponentKey(data, clazz.qualifiedName.orEmpty())

        return (subcomponents[key] ?: buildSubcomponent(data, clazz)
            .also { subcomponent -> subcomponents[key] = subcomponent }) as SUBCOMPONENT
    }

    inline fun <reified SUBCOMPONENT : BaseApi> getSubcomponent(data: Any): SUBCOMPONENT =
        getSubcomponent(SUBCOMPONENT::class, data)

    fun <SUBCOMPONENT : BaseApi> clearSubcomponent(clazz: KClass<SUBCOMPONENT>, data: Any) {
        beforeSubcomponentClear(clazz, data)
        subcomponents.remove(SubcomponentKey(data, clazz.qualifiedName.orEmpty()))
    }

    inline fun <reified SUBCOMPONENT : BaseApi> clearSubcomponent(data: Any) {
        clearSubcomponent(SUBCOMPONENT::class, data)
    }

    protected open fun beforeSubcomponentClear(clazz: KClass<*>, data: Any) {
        // For my children
    }
}
