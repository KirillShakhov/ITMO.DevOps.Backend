package ru.ifmo.commons.dto

data class AuthenticationResponse(
    var username: String = "",
    var jwt: String = ""
)