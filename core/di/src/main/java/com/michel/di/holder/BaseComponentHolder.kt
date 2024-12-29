package com.michel.di.holder

import com.michel.di.model.BaseDependencies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

abstract class BaseComponentHolder<DEPENDENCIES : BaseDependencies> {

    protected val componentScopeContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.Default

    protected open fun onAfterInit() {
        // For my children
    }
}
