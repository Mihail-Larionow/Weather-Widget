plugins {
    id("org.jetbrains.kotlin.plugin.serialization")
    id("android-compose-convention")
    id("kotlin-parcelize")
    id("unit-tests")
}

android {
    namespace = "com.michel.feature.navigation"
}

dependencies {
    implementation(projects.core.di)
    implementation(projects.core.mvi)
    implementation(projects.core.utils)
    implementation(projects.core.designsystem)
    implementation(projects.core.navigation.impl)

    implementation(projects.feature.weather)
    implementation(projects.feature.profile)
    implementation(projects.feature.settings)
    implementation(projects.feature.deeplinks)
    implementation(projects.feature.appInformation)

    ksp(libs.dagger.compiler)
}
