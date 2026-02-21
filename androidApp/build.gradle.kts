plugins {
    alias(libs.plugins.convention.android.application.compose)
}

android {
    namespace = "com.yourssu.pingpong.android"
}

dependencies {
    implementation(projects.composeApp)

    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)

    implementation(libs.androidx.activity.compose)
}
