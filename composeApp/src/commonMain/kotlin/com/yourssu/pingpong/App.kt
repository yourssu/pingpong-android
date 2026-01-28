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
import androidx.lifecycle.viewmodel.compose.viewModel
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
    googleAuthManager: GoogleAuthManager,
    // ViewModel을 주입
    viewModel: AuthViewModel = viewModel { AuthViewModel() }
) {
    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // ViewModel의 상태를 참조하여 UI를 그립니다.
            if (!viewModel.isLoggedIn) {
                Button(
                    onClick = { viewModel.loginWithKakao(loginHandler) },
                    modifier = Modifier.fillMaxWidth(0.7f)
                ) {
                    Text("카카오 로그인")
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { viewModel.loginWithGoogle(googleAuthManager) },
                    modifier = Modifier.fillMaxWidth(0.7f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text("Google 로그인")
                }
            } else {
                Text(if (viewModel.userName.isEmpty()) "로그인되었습니다." else "${viewModel.userName}님 환영합니다!")
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { viewModel.logout(loginHandler, googleAuthManager) },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("로그아웃")
                }
            }
        }
    }
}