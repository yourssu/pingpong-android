package com.yourssu.pingpong

import android.content.Context
import com.kakao.sdk.user.UserApiClient

class AndroidSocialLoginHandler(private val context: Context) : SocialLoginHandler {

    override fun loginWithKakao(onSuccess: (String) -> Unit, onFailure: (Throwable) -> Unit) {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) onFailure(error)
                else if (token != null) onSuccess(token.accessToken)
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
                if (error != null) onFailure(error)
                else if (token != null) onSuccess(token.accessToken)
            }
        }
    }

    override fun logout(onResult: (Throwable?) -> Unit) {
        // 카카오 SDK 로그아웃 호출
        UserApiClient.instance.logout { error ->
            onResult(error)
        }
    }
}