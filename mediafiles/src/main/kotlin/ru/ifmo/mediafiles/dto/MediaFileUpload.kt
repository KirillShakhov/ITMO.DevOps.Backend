package ru.ifmo.mediafiles.dto

import ru.ifmo.mediafiles.model.MediaType

data class MediaFileUpload(
    var username: String,
    var type: MediaType,
    var base64: String
)