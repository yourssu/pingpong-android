package auth

// 사용자 정보를 담을 데이터 클래스!
data class GoogleUser(
    val idToken: String,
    val displayName: String?,
    val email: String?
)

// 플랫폼별 구현을 위한 expect 클래스
expect class GoogleAuthManager {
    suspend fun signIn(): GoogleUser?
    suspend fun signOut()
}