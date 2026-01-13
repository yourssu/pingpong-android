plugins {
    id("pingpong.kmp.library")
}

android {
    namespace = "com.yourssu.pingpong.feature.settings.api"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.model)
        }
    }
}
