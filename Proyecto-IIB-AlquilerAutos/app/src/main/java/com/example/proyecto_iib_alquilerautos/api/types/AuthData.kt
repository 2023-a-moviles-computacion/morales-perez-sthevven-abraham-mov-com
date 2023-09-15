package com.example.proyecto_iib_alquilerautos.api.types

data class AuthData(
    val userId: Long,
    val token: String,
    val role: String,
)
