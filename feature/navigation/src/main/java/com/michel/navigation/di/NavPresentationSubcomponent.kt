package com.michel.navigation.di

import com.michel.di.model.BaseApi
import com.michel.di.scope.SubcomponentScope
import com.michel.navigation.domain.MainDestinationRegistrar
import com.michel.navigation.presentation.MainNavViewModel
import dagger.Subcomponent
import javax.inject.Provider

@SubcomponentScope
@Subcomponent
internal interface NavPresentationSubcomponent : BaseApi {

    val viewModelProvider: Provider<MainNavViewModel>
    val registrar: MainDestinationRegistrar
}
