plugins {
    alias(libs.plugins.kotlin.serialization)
    id("android-compose-convention")
}

android {
    namespace = "com.michel.impl"
}

dependencies {
    api(projects.core.navigation.api)

    api(libs.kotlinx.serialization.json)
    api(libs.kotlinx.serialization.core)
    api(libs.androidx.navigation.compose)

    implementation(libs.androidx.runtime.android)
    implementation(libs.androidx.animation.android)
    implementation(libs.androidx.navigation.runtime.ktx)
}
