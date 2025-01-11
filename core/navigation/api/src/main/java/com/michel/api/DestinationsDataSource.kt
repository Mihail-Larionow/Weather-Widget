package com.michel.api

interface DestinationsDataSource<R : Any> {

    operator fun invoke(): Set<NavDestination<out R>>
}
