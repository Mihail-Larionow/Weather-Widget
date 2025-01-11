plugins {
   id("kotlin-pure-convention")
}

dependencies {
    api(libs.test.kotest.assertions.core)
    api(libs.test.coroutines)
    api(libs.test.mockk.core)
    api(libs.test.turbine)
    api(libs.test.junit)
}
