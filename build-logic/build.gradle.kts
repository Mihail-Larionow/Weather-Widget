plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        create("unit-tests") {
            id = "unit-tests"
            implementationClass = "com.michel.plugins.convention.test.UnitTestPlugin"
        }
    }
}

dependencies {

    implementation(libs.ksp.gradle.plugin)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.android.gradle.plugin)
    implementation(libs.compose.gradle.plugin)

    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}
