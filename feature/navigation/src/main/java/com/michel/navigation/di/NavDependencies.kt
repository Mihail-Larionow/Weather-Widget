package com.michel.navigation.di

import com.michel.di.model.BaseDependencies
import com.michel.ui.activity.ActivityRegistry

interface NavDependencies : BaseDependencies {
    val activityRegistry: ActivityRegistry
}
