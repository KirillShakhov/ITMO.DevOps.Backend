package ru.ifmo.auth.model
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("users-auth")
data class UserAuth(
    @Id
    val id: String?,
    var email: String,
    var password: String,
)