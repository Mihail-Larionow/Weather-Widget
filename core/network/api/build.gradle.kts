plugins {
    id("kotlin-pure-convention")
}
dependencies {
    api(libs.gson)
    api(libs.ktor.client.core)
    api(libs.ktor.client.json)
    api(libs.ktor.client.okhttp)
    api(libs.ktor.client.logging)
    api(libs.ktor.client.serialization)
    api(libs.ktor.client.content.negotiation)
}
