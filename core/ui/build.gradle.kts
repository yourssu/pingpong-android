plugins {
    id("pingpong.kmp.library")
    id("pingpong.kmp.compose")
}

android {
    namespace = "com.yourssu.pingpong.core.ui"
}


kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.designsystem) // 디자인 시스템 의존
            implementation(projects.core.model)
        }
    }
}
