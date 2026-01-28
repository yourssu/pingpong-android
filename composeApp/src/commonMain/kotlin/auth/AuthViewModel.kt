package com.yourssu.pingpong

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import auth.GoogleAuthManager
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    // UI에서 관찰할 상태 (내부에서만 수정 가능하도록 private set 설정)
    var isLoggedIn by mutableStateOf(false)
        private set

    var userName by mutableStateOf("")
        private set

    // 카카오 로그인 로직
    fun loginWithKakao(loginHandler: SocialLoginHandler) {
        loginHandler.loginWithKakao(
            onSuccess = {
                isLoggedIn = true
            },
            onFailure = { error ->
                println("카카오 로그인 실패: ${error.message}")
            }
        )
    }

    // 구글 로그인 로직 (코루틴 사용)
    fun loginWithGoogle(googleAuthManager: GoogleAuthManager) {
        viewModelScope.launch {
            val user = googleAuthManager.signIn()
            if (user != null) {
                userName = user.displayName ?: "구글 사용자"
                isLoggedIn = true
            }
        }
    }

    // 로그아웃 로직
    fun logout(loginHandler: SocialLoginHandler, googleAuthManager: GoogleAuthManager) {
        viewModelScope.launch {
            loginHandler.logout { }
            googleAuthManager.signOut()
            isLoggedIn = false
            userName = ""
        }
    }
}