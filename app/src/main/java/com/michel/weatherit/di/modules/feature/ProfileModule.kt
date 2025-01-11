package com.michel.weatherit.di.modules.feature

import com.michel.api.FeatureNavApi
import com.michel.navigation.presentation.navigation.NavApi
import com.michel.profile.di.ProfileDependencies
import com.michel.profile.navigation.ProfileNavDirection
import dagger.Module
import dagger.Provides

@Module
object ProfileModule {

    @Provides
    fun provideProfileDependencies(
        navApi: NavApi,
    ): ProfileDependencies = object : ProfileDependencies {

        override val profileFeatureNavApi: FeatureNavApi<ProfileNavDirection> = navApi.profileNavApi
    }
}
