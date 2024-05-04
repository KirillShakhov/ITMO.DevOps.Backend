package ru.ifmo.commons.dto

data class ApiResponse(
    var success: Boolean = false,
    var message:String = ""
)