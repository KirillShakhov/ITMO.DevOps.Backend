package ru.ifmo.commons.dto

data class LoginRequest(
    var username: String = "",
    var password: String = ""
)