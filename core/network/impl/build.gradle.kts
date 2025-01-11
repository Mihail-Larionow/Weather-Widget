plugins {
    id("android-core-convention")
    id("kotlin-ksp-convention")
}

dependencies {
    api(projects.core.network.api)

    implementation(projects.core.di)

    ksp(libs.dagger.compiler)
}
