package ru.ifmo.mediafiles.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*


@Document("files")
data class MediaFile(
    @Id
    val id: String?,
    var username: String,
    var type: MediaType,
    var base64: String,
    @CreatedDate
    var createdDate: Date?,
)