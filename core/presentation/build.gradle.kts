plugins {
    alias(libs.plugins.convention.cmp.library)
}

kotlin {
    androidLibrary {
        namespace = "com.yourssu.pingpong.core.presentation"
        compileSdk = libs.versions.projectCompileSdkVersion.get().toInt()
        minSdk = libs.versions.projectMinSdkVersion.get().toInt()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.jetbrains.compose.viewmodel)
            implementation(libs.jetbrains.lifecycle.viewmodel)
            implementation(libs.jetbrains.lifecycle.compose)
            implementation(libs.kotlinx.coroutines.core)
            implementation(projects.core.domain)
        }
    }
}
