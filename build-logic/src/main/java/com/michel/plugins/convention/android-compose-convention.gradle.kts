import com.michel.plugins.convention.tools.JavaConstants
import gradle.kotlin.dsl.accessors._cb41af676f88d94563fb18deaa5153de.android
import gradle.kotlin.dsl.accessors._cb41af676f88d94563fb18deaa5153de.implementation
import org.gradle.kotlin.dsl.dependencies
import com.michel.plugins.convention.tools.libs

plugins {
    id("com.android.library")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

kotlin {
    jvmToolchain(JavaConstants.JVM_VERSION)
}

android {
    baseAndroidConfig()
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
}

