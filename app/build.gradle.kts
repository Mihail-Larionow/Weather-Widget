
plugins {
    id("android-app-convention")
    id("unit-tests")
    id("com.dpforge.manifestguard")
}

android {
    namespace = "com.michel.weatherit"
}

manifestGuard {
    defaultConfig {
        enabled = true
        compareOnAssemble = false
        ignore {
            ignoreAppVersionChanges = true
        }
    }
}

dependencies {
    implementation(projects.core.di)
    implementation(projects.core.mvi)
    implementation(projects.core.utils)
    implementation(projects.core.network.impl)
    implementation(projects.core.designsystem)
    implementation(projects.core.navigation.impl)

    implementation(projects.feature.weather)
    implementation(projects.feature.profile)
    implementation(projects.feature.settings)
    implementation(projects.feature.deeplinks)
    implementation(projects.feature.navigation)
    implementation(projects.feature.appInformation)

    ksp(libs.dagger.compiler)
}
