package dto

import java.util.*

data class MessageDto(
    var id: String?,
    var username: String,
    var recipient: String,
    var text: String,
    var attachment: Int?,
    var createdDate: Date?,
)
