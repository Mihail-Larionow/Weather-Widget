package com.michel.weatherit.di.modules

import com.michel.navigation.base.FeatureNavApi
import com.michel.navigation.presentation.navigation.base.NavApi
import com.michel.profile.di.ProfileDependencies
import com.michel.profile.navigation.ProfileNavDirection
import com.michel.ui.activity.ActivityRegistry
import dagger.Module
import dagger.Provides

@Module
object ProfileModule {

    @Provides
    fun provideProfileDependencies(
        navApi: NavApi,
        activityRegistry: ActivityRegistry,
    ): ProfileDependencies = object : ProfileDependencies {

        override val profileFeatureNavApi: FeatureNavApi<ProfileNavDirection> = navApi.profileNavApi

        override val activityRegistry: ActivityRegistry = activityRegistry
    }
}
