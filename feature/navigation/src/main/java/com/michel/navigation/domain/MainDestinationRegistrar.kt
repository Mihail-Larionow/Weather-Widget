package com.michel.navigation.domain

import com.michel.impl.DestinationRegistrar
import javax.inject.Inject

internal class MainDestinationRegistrar @Inject constructor(
    dataSource: MainDestinationDataSource,
) : DestinationRegistrar by DestinationRegistrar(dataSource)
