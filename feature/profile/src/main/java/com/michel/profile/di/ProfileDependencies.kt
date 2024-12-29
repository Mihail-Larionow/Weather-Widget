package com.michel.profile.di

import com.michel.di.model.BaseDependencies
import com.michel.navigation.base.FeatureNavApi
import com.michel.profile.navigation.ProfileNavDirection
import com.michel.ui.activity.ActivityRegistry

interface ProfileDependencies : BaseDependencies {

    val profileFeatureNavApi: FeatureNavApi<ProfileNavDirection>

    val activityRegistry: ActivityRegistry
}
