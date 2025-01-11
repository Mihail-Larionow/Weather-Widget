plugins {
    id("android-compose-convention")
}

android {
    namespace = "com.michel.mvi"
}

dependencies {
    api(libs.androidx.activity.compose)

    implementation(projects.core.di)
    implementation(projects.core.utils)
    implementation(projects.core.designsystem)
}
