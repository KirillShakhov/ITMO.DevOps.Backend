package ru.ifmo.mediafiles.dto

import ru.ifmo.mediafiles.model.MediaType

data class MediaFileUpload(
    var type: MediaType,
    var base64: String
)