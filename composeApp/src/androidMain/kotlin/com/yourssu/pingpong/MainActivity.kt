package com.yourssu.pingpong

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import auth.GoogleAuthManager
import java.security.MessageDigest
import com.kakao.sdk.common.KakaoSdk //카카오용
import com.yourssu.pingpong.BuildConfig

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        // 카카오 SDK 초기화 (네이티브 앱 키 입력)
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)
        setContent {
            val _loginHandler = remember { AndroidSocialLoginHandler(this) }
            val googleAuthManager = GoogleAuthManager(this) // Context 전달
            App(
                loginHandler = _loginHandler,
                googleAuthManager = googleAuthManager
            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    // 1. 카카오 로그인용 가짜 핸들러 (기존)
    val previewHandler = object : SocialLoginHandler {
        override fun loginWithKakao(onSuccess: (String) -> Unit, onFailure: (Throwable) -> Unit) {
            println("Preview: 카카오 로그인 클릭됨")
        }
        override fun logout(onResult: (Throwable?) -> Unit) {
            onResult(null)
        }
    }

    // 2. 구글 로그인용 가짜 매니저
    // 현재 클래스 형태라면, Context가 필요한 actual class 구조상
    // 프리뷰 전용으로 null이나 더미 Context를 넣은 인스턴스가 필요할 수 있습니다.
    // 여기서는 로직 연결을 위해 '임시'로 생성하는 코드를 예시로 듭니다.
    val context = androidx.compose.ui.platform.LocalContext.current
    val previewGoogleManager = remember { GoogleAuthManager(context) }

    App(
        loginHandler = previewHandler,
        googleAuthManager = previewGoogleManager
    )
}