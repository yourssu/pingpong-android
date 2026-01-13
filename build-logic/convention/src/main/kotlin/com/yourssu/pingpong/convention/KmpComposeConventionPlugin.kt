package com.yourssu.pingpong.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.compose")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            // Compose Compiler 설정이 필요한 경우 추가
            // val composeExtension = extensions.getByType<org.jetbrains.compose.ComposeExtension>()
            // composeExtension.kotlinCompilerPlugin.set("...")
        }
    }
}