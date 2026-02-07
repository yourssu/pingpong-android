plugins {
    alias(libs.plugins.convention.kmp.library)
}

kotlin {
    androidLibrary {
        namespace = "com.yourssu.pingpong.feature.settings.api"
        compileSdk = libs.versions.projectCompileSdkVersion.get().toInt()
        minSdk = libs.versions.projectMinSdkVersion.get().toInt()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.model)
        }
    }
}
