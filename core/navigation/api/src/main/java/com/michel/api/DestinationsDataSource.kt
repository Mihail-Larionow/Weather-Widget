package com.michel.api

interface DestinationsDataSource<ROUTE : Any> {

    operator fun invoke(): Set<NavDestination<out ROUTE>>
}
