import com.android.build.gradle.BaseExtension
import com.michel.plugins.convention.tools.AndroidConstants
import com.michel.plugins.convention.tools.JavaConstants
import com.michel.plugins.convention.tools.kotlinOptions

fun BaseExtension.baseAndroidConfig() {

    namespace = "com.michel.weatherit"
    setCompileSdkVersion(AndroidConstants.COMPILE_SDK)

    defaultConfig {
        minSdk = AndroidConstants.MIN_SDK
        targetSdk = AndroidConstants.TARGET_SDK
        versionCode = AndroidConstants.VERSION_CODE
        versionName = AndroidConstants.VERSION_NAME

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaConstants.COMPILE_JDK_VERSION
        targetCompatibility = JavaConstants.COMPILE_JDK_VERSION
    }

    kotlinOptions {
        jvmTarget = JavaConstants.KOTLIN_JVM_TARGET
    }
}
