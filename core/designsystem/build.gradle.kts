plugins {
    id("pingpong.kmp.library")
    id("pingpong.kmp.compose") // Compose 사용
}

android {
    namespace = "com.yourssu.pingpong.core.designsystem"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            // composeApp에서 쓰던 UI 관련 의존성 이동
        }
    }
}
