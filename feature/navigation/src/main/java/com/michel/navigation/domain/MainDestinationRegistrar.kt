package com.michel.navigation.domain

import com.michel.impl.Registrar
import javax.inject.Inject

internal class MainDestinationRegistrar @Inject constructor(
    dataSource: MainDestinationDataSource,
) : Registrar by Registrar(dataSource)
