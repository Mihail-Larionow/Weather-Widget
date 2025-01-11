plugins {
    id("android-core-convention")
}

android {
    namespace = "com.michel.di"
}

dependencies {
    api(libs.dagger.pure)
    api(libs.androidx.fragment.ktx)
    api(libs.dagger.android.support)
}
