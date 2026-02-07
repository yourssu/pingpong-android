plugins {
    alias(libs.plugins.convention.cmp.library)
}

kotlin {
    androidLibrary {
        namespace = "com.yourssu.pingpong.core.designsystem"
        compileSdk = libs.versions.projectCompileSdkVersion.get().toInt()
        minSdk = libs.versions.projectMinSdkVersion.get().toInt()
    }

    sourceSets {
        commonMain.dependencies {
            // Design system base dependencies are provided by convention plugin
        }
    }
}
