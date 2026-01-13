plugins {
    id("pingpong.kmp.library")
}

android {
    namespace = "com.yourssu.pingpong.core.data"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.model)
            implementation(libs.kotlinx.coroutines.core)
        }
    }
}