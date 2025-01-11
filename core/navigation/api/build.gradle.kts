plugins {
    id("android-compose-convention")
}

android {
    namespace = "com.michel.api"
}

dependencies {
    implementation(libs.androidx.runtime.android)
}
