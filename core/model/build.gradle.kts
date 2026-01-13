plugins {
    id("pingpong.kmp.library")
}

android {
    namespace = "com.yourssu.pingpong.core.model"
}


kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json) // 필요시
        }
    }
}

