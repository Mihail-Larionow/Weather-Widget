package com.michel.profile.di

import com.michel.api.FeatureNavApi
import com.michel.di.model.BaseDependencies
import com.michel.profile.navigation.ProfileNavDirection

interface ProfileDependencies : BaseDependencies {

    val profileFeatureNavApi: FeatureNavApi<ProfileNavDirection>
}
