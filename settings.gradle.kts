rootProject.name = "WeatherIt"

enableFeaturePreview("STABLE_CONFIGURATION_CACHE")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

include(":app")
include(":feature")
include(":feature:weather")
include(":feature:profile")
include(":feature:settings")
include(":feature:navigation")
include(":feature:app-information")
include(":core")
include(":core:di")
include(":core:designsystem")
include(":core:mvi")
include(":core:utils")
include(":core:navigation")
include(":core:navigation:api")
include(":core:navigation:impl")
include(":core:test")
include(":core:test:unit")
include(":core:test:snapshot")
include(":core:network")
include(":core:network:impl")
include(":core:network:api")
