import com.michel.plugins.convention.tools.JavaConstants
import com.michel.plugins.convention.tools.libs

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

kotlin {
    jvmToolchain(JavaConstants.JVM_VERSION)
}

android {
    baseAndroidConfig()
}

dependencies {
    implementation(libs.androidx.core.ktx)
}

