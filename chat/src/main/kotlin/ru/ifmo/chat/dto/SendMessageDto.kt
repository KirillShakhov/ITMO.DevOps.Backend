package ru.ifmo.chat.dto


data class SendMessageDto(
    var text: String,
    var recipient: String,
    var attachment: Int?,
)
