package ru.ifmo.commons.dto

import java.util.*

data class MessageDto(
    var id: String? = "",
    var username: String = "",
    var recipient: String = "",
    var text: String = "",
    var attachment: Int? = null,
    var createdDate: Date? = null,
)
