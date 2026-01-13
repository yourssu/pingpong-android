plugins {
    `kotlin-dsl`
}

group = "com.yourssu.pingpong.buildlogic"

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.compose.gradlePlugin)
}

gradlePlugin {
    plugins {
        create("androidApplication") {
            id = "pingpong.android.application"
            implementationClass = "com.yourssu.pingpong.convention.AndroidApplicationConventionPlugin"
        }
        create("androidLibrary") {
            id = "pingpong.android.library"
            implementationClass = "com.yourssu.pingpong.convention.AndroidLibraryConventionPlugin"
        }
        create("kmpLibrary") {
            id = "pingpong.kmp.library"
            implementationClass = "com.yourssu.pingpong.convention.KmpLibraryConventionPlugin"
        }
        create("kmpCompose") {
            id = "pingpong.kmp.compose"
            implementationClass = "com.yourssu.pingpong.convention.KmpComposeConventionPlugin"
        }
    }
}