rootProject.name = "Pingpong"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

include(":composeApp")
include(":androidApp")

// Core 모듈 (도메인, 데이터, 디자인 시스템, 프레젠테이션)
include(":core:domain")
include(":core:data")
include(":core:designsystem")
include(":core:presentation")

// Feature 모듈
include(":feature:auth:domain")
include(":feature:auth:data")
include(":feature:auth:presentation")

include(":feature:groupchat:domain")
include(":feature:groupchat:data")
include(":feature:groupchat:presentation")

include(":feature:messaging:domain")
include(":feature:messaging:data")
include(":feature:messaging:presentation")