plugins {
    alias(libs.plugins.convention.cmp.library)
}

kotlin {
    androidLibrary {
        namespace = "com.yourssu.pingpong.core.ui"
        compileSdk = libs.versions.projectCompileSdkVersion.get().toInt()
        minSdk = libs.versions.projectMinSdkVersion.get().toInt()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.designsystem)
            implementation(projects.core.model)
        }
    }
}
