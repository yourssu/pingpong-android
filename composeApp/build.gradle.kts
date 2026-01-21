import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties
import kotlin.apply

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}
val localProperties = Properties().apply {
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        localPropertiesFile.inputStream().use { load(it) }
    }
}
kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            //카카오 소셜 로그인
            implementation("com.kakao.sdk:v2-user:2.20.1")
            // Android Credential Manager (구글 소셜 로그인)
            implementation("androidx.credentials:credentials:1.2.2")
            implementation("androidx.credentials:credentials-play-services-auth:1.2.2")
            implementation("com.google.android.libraries.identity.googleid:googleid:1.1.1")
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.androidx.lifecycle.viewmodel.compose)
            implementation(libs.androidx.lifecycle.runtime.compose)

            implementation(projects.core.designsystem)
            implementation(projects.core.model)
            implementation(projects.core.ui)

            implementation(projects.feature.settings.impl)
            // Coroutines for async flow
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.yourssu.pingpong"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    buildFeatures {
        buildConfig = true
    }
    defaultConfig {
        applicationId = "com.yourssu.pingpong"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "KAKAO_NATIVE_APP_KEY", "\"${localProperties.getProperty("KAKAO_NATIVE_APP_KEY")}\"")
        manifestPlaceholders["KAKAO_NATIVE_APP_KEY"] = localProperties.getProperty("KAKAO_NATIVE_APP_KEY") ?: ""

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}