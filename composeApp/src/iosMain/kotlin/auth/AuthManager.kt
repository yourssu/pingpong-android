// iosMain/kotlin/auth/AuthManager.kt
package auth

actual class GoogleAuthManager {
    actual suspend fun signIn(): GoogleUser? {
        // iOS용 GoogleSignIn SDK 호출 로직
        return null
    }

    actual suspend fun signOut() {
        // iOS 로그아웃 로직
    }
}