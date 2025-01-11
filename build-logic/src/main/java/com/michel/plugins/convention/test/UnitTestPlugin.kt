package com.michel.plugins.convention.test

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project
import org.gradle.kotlin.dsl.withType

class UnitTestPlugin : Plugin<Project> {

    override fun apply(target: Project) {

        with(target) {
            tasks.withType<Test>().configureEach {
                useJUnit()
            }

            plugins.matching { it.isAndroidPlugin() }
                .whenPluginAdded {
                    configure<com.android.build.gradle.BaseExtension> {
                        testOptions {
                            unitTests {
                                all(Test::useJUnit)
                            }
                        }
                    }
                }

            dependencies {
                add("testImplementation", project(":core:test:unit"))
            }
        }
    }

    private fun Plugin<*>?.isAndroidPlugin(): Boolean =
        this?.javaClass?.packageName?.startsWith("com.android.build") == true
}
