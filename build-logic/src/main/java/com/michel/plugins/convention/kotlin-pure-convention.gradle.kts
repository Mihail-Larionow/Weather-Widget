import com.michel.plugins.convention.tools.JavaConstants

plugins {
    id("kotlin")
}

kotlin {
    jvmToolchain(JavaConstants.JVM_VERSION)
}
