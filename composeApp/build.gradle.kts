plugins {
    alias(libs.plugins.convention.cmp.application)
}

kotlin {
    androidLibrary {
        namespace = "com.yourssu.pingpong"
        compileSdk = libs.versions.projectCompileSdkVersion.get().toInt()
        minSdk = libs.versions.projectMinSdkVersion.get().toInt()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.jetbrains.compose.viewmodel)
            implementation(libs.jetbrains.lifecycle.compose)

            implementation(projects.core.designsystem)
            implementation(projects.core.model)
            implementation(projects.core.ui)

            implementation(projects.feature.settings.impl)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
