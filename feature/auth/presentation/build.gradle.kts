plugins {
    alias(libs.plugins.convention.cmp.feature)
}

kotlin {
    androidLibrary {
        namespace = "com.yourssu.pingpong.auth.presentation"
        compileSdk = libs.versions.projectCompileSdkVersion.get().toInt()
        minSdk = libs.versions.projectMinSdkVersion.get().toInt()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.domain)
            implementation(projects.core.designsystem)
            implementation(projects.core.presentation)
            implementation(projects.feature.auth.domain)
        }
    }
}
