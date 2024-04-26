package ru.ifmo.auth.dto

data class LoginRequest(
    var username: String,
    var password: String
)