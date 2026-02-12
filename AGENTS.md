# Repository Guidelines

## Project Structure & Module Organization
This is a Kotlin Multiplatform + Compose Multiplatform project.
- `composeApp/`: shared UI/application layer (`commonMain`, `androidMain`, `iosMain`, `commonTest`).
- `androidApp/`: Android entrypoint (`MainActivity`), manifest, and Android resources.
- `iosApp/`: Xcode project and SwiftUI iOS host app.
- `core/`: shared layers (`domain`, `data`, `designsystem`, `presentation`).
- `feature/`: feature modules split by domain (`auth`, `groupchat`, `messaging`) with `domain/data/presentation`.
- `build-logic/`: convention plugins used by all modules (included build).

## Build, Test, and Development Commands
Use the Gradle wrapper from repo root.
- `./gradlew :androidApp:assembleDebug`: build Android debug app.
- `./gradlew :composeApp:assembleDebug`: build shared Compose module for Android target.
- `./gradlew build`: compile all modules and run configured tests.
- `./gradlew test`: run JVM/unit tests.
- `./gradlew :androidApp:connectedDebugAndroidTest`: run instrumentation tests on a connected device/emulator.
- `./gradlew check`: run verification tasks.
For iOS, open `iosApp/iosApp.xcodeproj` in Xcode and run the `iosApp` scheme.

## Coding Style & Naming Conventions
- Language: Kotlin (`.kts` for build scripts), 4-space indentation.
- Follow Kotlin style defaults in IDE; keep trailing commas where existing code uses them.
- Packages use `com.yourssu.pingpong...`; match module responsibility in namespace.
- Module naming follows layered conventions: `:core:<layer>`, `:feature:<name>:<layer>`.
- Prefer convention plugins from `build-logic` over per-module custom Gradle setup.

## Testing Guidelines
- Place tests in source-set-specific folders: `commonTest`, `androidUnitTest`, `androidInstrumentedTest`.
- Use `kotlin.test` for shared tests; JUnit for JVM/Android where needed.
- Test file naming: `<ClassName>Test.kt`; keep test names descriptive and behavior-focused.
- Add or update tests for any non-trivial logic changes in `core` or `feature` modules.

## Commit & Pull Request Guidelines
- Follow the existing commit pattern: `<type>: <summary>` (examples: `feat: ...`, `fix: ...`, `refactor: ...`, `chore: ...`).
- Keep commits focused by module or feature area.
- PRs should include:
  - concise description of scope and rationale,
  - linked issue/ticket (if available),
  - screenshots/video for UI changes (Android/iOS),
  - notes on tested targets (`android`, `ios`, `common`).

## Security & Configuration Tips
- Keep secrets out of VCS; use `local.properties` for local keys.
- If using BuildKonfig-backed values, ensure required properties (for example `API_KEY`) exist locally before building.
