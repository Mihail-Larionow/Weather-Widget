plugins {
    id("android-compose-convention")
    id("unit-tests")
}

android {
    namespace = "com.michel.designsystem"
}

dependencies {
    api(libs.haze)
    api(libs.androidx.ui)
    api(libs.androidx.ui.tooling)
    api(libs.androidx.material.android)
    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.material.icons.core.android)
}
