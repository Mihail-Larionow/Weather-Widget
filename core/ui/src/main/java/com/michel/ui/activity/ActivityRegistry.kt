package com.michel.ui.activity

import android.app.Activity
import kotlin.reflect.KClass

interface ActivityRegistry {

    fun get(): Activity?

    fun <TYPE : Any> getOfType(clazz: KClass<TYPE>): TYPE?

    fun registerActivityCallback(callback: ActivityCallback)

    fun removeActivityCallback(callback: ActivityCallback)
}

inline fun <reified TYPE : Any> ActivityRegistry.getOfType(): TYPE? =
    getOfType(TYPE::class)
