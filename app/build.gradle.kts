
plugins {
    id("android-app-convention")
    id("unit-tests")
}

android {
    namespace = "com.michel.weatherit"
}

dependencies {
    implementation(projects.core.di)
    implementation(projects.core.mvi)
    implementation(projects.core.utils)
    implementation(projects.core.network.impl)
    implementation(projects.core.designsystem)
    implementation(projects.feature.navigation)
    implementation(projects.core.navigation.impl)

    implementation(projects.feature.weather)
    implementation(projects.feature.profile)
    implementation(projects.feature.settings)
    implementation(projects.feature.appInformation)

    ksp(libs.dagger.compiler)
}
