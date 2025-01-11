plugins {
    id("android-compose-convention")
}

android {
    namespace = "com.michel.api"
}

dependencies {
    api(libs.androidx.navigation.common.ktx)

    implementation(libs.androidx.runtime.android)
}
