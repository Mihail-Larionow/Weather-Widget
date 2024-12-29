package com.michel.profile.di

import com.michel.di.model.BaseApi
import com.michel.profile.presentation.ProfileFragment

interface ProfileApi : BaseApi {

    fun inject(fragment: ProfileFragment)
}
