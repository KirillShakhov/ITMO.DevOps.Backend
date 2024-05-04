package ru.ifmo.commons.dto


data class SendMessageDto(
    var text: String = "",
    var recipient: String = "",
    var attachment: Int? = null,
)
