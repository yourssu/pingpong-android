// composeApp/src/androidMain/kotlin/auth/AuthManager.kt
package auth

import android.content.Context
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

actual class GoogleAuthManager(private val context: Context) {
    private val credentialManager = CredentialManager.create(context)

    actual suspend fun signIn(): GoogleUser? = withContext(Dispatchers.IO) {
        try {
            // Google Cloud Console에서 발급받은 '웹 애플리케이션' 클라이언트 ID를 넣으세요.
            val googleIdOption = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId("959466703756-co3djr6qc398j3jumc2n1u93tlqs5j5n.apps.googleusercontent.com")
                .setAutoSelectEnabled(false)
                .build()

            val request = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

            val result = credentialManager.getCredential(context, request)
            val credential = result.credential

            if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                // credential.data(Bundle)로부터 객체를 명시적으로 생성합니다.
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)

                GoogleUser(
                    idToken = googleIdTokenCredential.idToken,
                    displayName = googleIdTokenCredential.displayName,
                    email = googleIdTokenCredential.id
                )
            } else {
                println("DEBUG: 예상치 못한 크리덴셜 타입: ${credential.type}")
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    actual suspend fun signOut() {
        credentialManager.clearCredentialState(ClearCredentialStateRequest())
    }
}