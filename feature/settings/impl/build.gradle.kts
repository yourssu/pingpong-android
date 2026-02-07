plugins {
    alias(libs.plugins.convention.cmp.library)
}

kotlin {
    androidLibrary {
        namespace = "com.yourssu.pingpong.feature.settings.impl"
        compileSdk = libs.versions.projectCompileSdkVersion.get().toInt()
        minSdk = libs.versions.projectMinSdkVersion.get().toInt()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.settings.api)
            implementation(projects.core.ui)
            implementation(projects.core.designsystem)
            implementation(projects.core.data)

            implementation(libs.androidx.lifecycle.viewmodel.compose)
        }
    }
}
