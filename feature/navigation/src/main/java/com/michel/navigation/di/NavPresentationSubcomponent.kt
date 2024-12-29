package com.michel.navigation.di

import com.michel.di.model.BaseApi
import com.michel.di.scope.SubcomponentScope
import com.michel.navigation.presentation.NavFragment
import dagger.Subcomponent

@SubcomponentScope
@Subcomponent(
    modules = [
        NavModule::class,
    ]
)
interface NavPresentationSubcomponent : BaseApi {

    fun inject(fragment: NavFragment)
}
