package com.yourssu.pingpong

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import auth.GoogleAuthManager
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import pingpong.composeapp.generated.resources.Res
import pingpong.composeapp.generated.resources.compose_multiplatform
import kotlin.io.encoding.Base64

@Composable
@Preview
fun App(
    loginHandler: SocialLoginHandler,
    googleAuthManager: GoogleAuthManager // 구글 매니저 추가
) {
    MaterialTheme {
        var isLoggedIn by remember { mutableStateOf(false) }
        var userName by remember { mutableStateOf("") }

        // suspend 함수 호출을 위한 코루틴 스코프
        val scope = rememberCoroutineScope()

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (!isLoggedIn) {
                // --- 카카오 로그인 버튼 ---
                Button(
                    onClick = {
                        loginHandler.loginWithKakao(
                            onSuccess = { token ->
                                println("카카오 로그인 성공")
                                isLoggedIn = true
                            },
                            onFailure = { error -> println("카카오 실패: ${error.message}") }
                        )
                    },
                    modifier = Modifier.fillMaxWidth(0.7f)
                ) {
                    Text("카카오 로그인")
                }

                Spacer(modifier = Modifier.height(12.dp))

                // --- 구글 로그인 버튼 추가 ---
                Button(
                    onClick = {
                        scope.launch {
                            val user = googleAuthManager.signIn()
                            if (user != null) {
                                println("구글 로그인 성공: ${user.displayName}")
                                userName = user.displayName ?: "구글 사용자"
                                isLoggedIn = true
                            } else {
                                println("구글 로그인 실패")
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(0.7f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text("Google 로그인")
                }

            } else {
                Text(if (userName.isEmpty()) "현재 로그인된 상태입니다." else "$userName 님 환영합니다!")
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        scope.launch {
                            // 양쪽 로그아웃 처리
                            loginHandler.logout { }
                            googleAuthManager.signOut()
                            isLoggedIn = false
                            userName = ""
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("로그아웃")
                }
            }
        }
    }
}