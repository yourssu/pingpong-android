package com.yourssu.pingpong

interface SocialLoginHandler {
    fun loginWithKakao(
        onSuccess: (accessToken: String) -> Unit,
        onFailure: (Throwable) -> Unit
    )
    fun logout(onResult: (Throwable?) -> Unit)
}