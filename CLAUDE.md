# CLAUDE.md - Pingpong Android Project

## Build Commands

```bash
# Build Android debug APK
./gradlew :androidApp:assembleDebug

# Install on device/emulator
./gradlew :androidApp:installDebug

# Run all tests
./gradlew test

# Build specific module
./gradlew :module:name:build
```

## Architecture Overview

- **Kotlin Multiplatform (KMP)** project targeting Android and iOS
- **Compose Multiplatform** for shared UI
- **Multi-module structure**: `core/`, `feature/`, `composeApp/`, `androidApp/`
- **Convention plugins** in `build-logic/` submodule (Gael-Android/build-logic)
- **Feature modules** split into `api/` (interfaces) and `impl/` (implementation)
- **AGP 9.0** compatible with KMP library plugin (`com.android.kotlin.multiplatform.library`)

## Module Dependency Flow

```
androidApp → composeApp → feature modules → core modules
```

- `androidApp`: Android application entry point (MainActivity, AndroidManifest, resources)
- `composeApp`: Shared KMP library with Compose UI (Android + iOS)

## Key Conventions

- **Plugin IDs**: `com.yourssu.convention.kmp.library`, `com.yourssu.convention.cmp.library`, `com.yourssu.convention.cmp.application`
- **Package naming**: `com.yourssu.pingpong.{module}`
- **Version catalog**: `gradle/libs.versions.toml`

## Tech Stack

- Kotlin 2.3.0, AGP 9.0.0, Compose Multiplatform 1.10.0
- Gradle 9.1.0
- Android: compileSdk 36, minSdk 24
- Kotlinx Coroutines 1.10.1, Serialization 1.8.0
