package com.michel.di.model

fun interface BaseDependenciesProvider<D : BaseDependencies, A : Any> {

    fun get(data: A): javax.inject.Provider<D>
}
