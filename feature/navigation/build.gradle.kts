plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.michel.feature.navigation"
    compileSdk = 34

    defaultConfig {
        minSdk = 27

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    dataBinding {
        android.buildFeatures.dataBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.javax.inject)
    implementation(libs.androidx.navigation.common.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(project(":core:mvi"))
    implementation(project(":core:utils"))
    implementation(project(":core:di"))
    implementation(project(":core:ui"))
    implementation(project(":feature:weather"))
    implementation(project(":feature:profile"))
    implementation(project(":feature:settings"))
    implementation(libs.androidx.runtime.android)
    implementation(project(":core:navigation"))
    implementation(libs.androidx.viewbinding)
    implementation(libs.androidx.databinding.runtime)
    debugImplementation(libs.androidx.ui.tooling)

    testImplementation(libs.junit)
    implementation(libs.dagger.pure)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    ksp(libs.dagger.compiler)
}
