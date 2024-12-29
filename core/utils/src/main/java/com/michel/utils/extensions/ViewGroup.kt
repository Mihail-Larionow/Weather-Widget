@file:Suppress("UNCHECKED_CAST")

package com.michel.utils.extensions

import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import kotlin.reflect.KClass

fun <T : View> ViewGroup.findViewByType(clazz: KClass<T>): T? {
    for (child in children) {
        when {
            clazz.isInstance(child) -> return child as T
            child is ViewGroup -> child.findViewByType(clazz)?.let { return it }
        }
    }
    return null
}

fun <T : View> ViewGroup.findViewsByType(clazz: KClass<T>): List<T?> {
    val result: MutableList<T?> = mutableListOf()
    this.findChildrenByType(clazz, result)
    return result
}

private fun <T : View> ViewGroup.findChildrenByType(clazz: KClass<T>, result: MutableList<T?>) {
    for (child in children) {
        when {
            clazz.isInstance(child) -> result.add(child as T)
            child is ViewGroup -> child.findChildrenByType(clazz, result)
        }
    }
}
