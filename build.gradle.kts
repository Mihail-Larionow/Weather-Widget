// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.detekt)
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.manifest.guard) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.serialization) apply false
}

detekt {
    parallel = true
    source.from(
        files(
            fileTree(baseDir = rootDir) {
                include("**/src/**/*.kt")
            }
        )
    )
    baseline = File(rootDir, "/config/detekt/baseline.xml")
    config.from(files(File(rootDir, "/config/detekt/detekt.yml")))
    ignoredVariants = listOf("release")
}
