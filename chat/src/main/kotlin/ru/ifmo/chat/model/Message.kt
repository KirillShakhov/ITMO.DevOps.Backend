package ru.ifmo.chat.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document("messages")
data class Message(
    @Id
    val id: String?,
    var username: String,
    var recipient: String,
    var text: String,
    var attachment: Int?,
    @CreatedDate
    var createdDate: Date?,
)