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
    }
}

include(":composeApp")

// Core 모듈 (도메인, 데이터, 디자인 시스템)
include(":core:model")
include(":core:designsystem")
include(":core:ui")
include(":core:data")

// Feature 모듈 (기능 단위 - 예시로 settings 추가)
include(":feature:settings:api")
include(":feature:settings:impl")