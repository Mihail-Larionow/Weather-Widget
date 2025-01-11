package com.michel.impl.di

import com.michel.api.data.NetworkDataSource
import com.michel.di.model.BaseApi

interface NetworkApi : BaseApi {

    val dataSource: NetworkDataSource
}
