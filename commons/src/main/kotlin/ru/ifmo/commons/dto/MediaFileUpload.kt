package ru.ifmo.commons.dto


data class MediaFileUpload(
    var type: MediaType,
    var base64: String
)