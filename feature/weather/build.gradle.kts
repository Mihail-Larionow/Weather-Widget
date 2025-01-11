plugins {
    id("android-compose-convention")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("unit-tests")
}

android {
    namespace = "com.michel.weather"
}

dependencies {
    implementation(projects.core.di)
    implementation(projects.core.mvi)
    implementation(projects.core.utils)
    implementation(projects.core.network.api)
    implementation(projects.core.designsystem)
    implementation(projects.core.navigation.impl)

    ksp(libs.dagger.compiler)
}
