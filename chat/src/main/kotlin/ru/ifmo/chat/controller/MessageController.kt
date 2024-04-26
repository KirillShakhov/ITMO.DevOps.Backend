package ru.ifmo.chat.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.ifmo.chat.dto.SendMessageDto
import ru.ifmo.chat.service.MessageService

@RestController
@RequestMapping("/messages")
class MessageController @Autowired constructor(
    private val messageService: MessageService
) {
    @GetMapping("/")
    fun getMediaFileByUsername(@RequestHeader("Username") username: String): ResponseEntity<*> {
        return ResponseEntity.ok(messageService.findMediaFileByUsername(username))
    }

    @PostMapping("/")
    fun saveMediaFile(
        @RequestHeader("Username") username: String,
        @RequestBody sendMessageDto: SendMessageDto
    ): ResponseEntity<*> {
        val message = messageService.sendMessage(username, sendMessageDto.recipient, sendMessageDto.text, sendMessageDto.attachment)
        return ResponseEntity.ok(message)
    }
}
