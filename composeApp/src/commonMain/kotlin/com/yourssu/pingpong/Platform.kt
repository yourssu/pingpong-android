package com.yourssu.pingpong

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform