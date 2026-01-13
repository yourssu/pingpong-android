package com.yourssu.pingpong.convention

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.multiplatform")
                apply("com.android.library")
            }

            extensions.configure<KotlinMultiplatformExtension> {
                androidTarget {
                    compilerOptions {
                        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
                    }
                }

                // iOS 타겟 설정
                listOf(
                    iosX64(),
                    iosArm64(),
                    iosSimulatorArm64()
                ).forEach { iosTarget ->
                    iosTarget.binaries.framework {
                        baseName = project.name
                        isStatic = true
                    }
                }

                // 기본 소스셋 계층 구조 적용 (commonMain < androidMain, iosMain 등)
                applyDefaultHierarchyTemplate()
            }

            // KMP 모듈도 안드로이드 설정을 가져야 하므로 공통 설정 적용
            extensions.configure<LibraryExtension> {
                compileSdk = 36
                defaultConfig {
                    minSdk = 24
                }
                compileOptions {
                    sourceCompatibility = org.gradle.api.JavaVersion.VERSION_11
                    targetCompatibility = org.gradle.api.JavaVersion.VERSION_11
                }
            }
        }
    }
}