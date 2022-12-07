package com.example.belajarlogindansplashscreen.api.data

data class LoginResponse(
    val access_token: String? = null,
    val user: Users? = null,
    val error: String? = null,
    val error_description: String? = null,
)
