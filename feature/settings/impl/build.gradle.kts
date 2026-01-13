plugins {
    id("pingpong.kmp.library")
    id("pingpong.kmp.compose")
}

android {
    namespace = "com.yourssu.pingpong.feature.settings.impl"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.settings.api) // 내 API 구현
            implementation(projects.core.ui)
            implementation(projects.core.designsystem)
            implementation(projects.core.data)

            // ViewModel 등 필요한 라이브러리
            implementation(libs.androidx.lifecycle.viewmodel.compose)
        }
    }
}
