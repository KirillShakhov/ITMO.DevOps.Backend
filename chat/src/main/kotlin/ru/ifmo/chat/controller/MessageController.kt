package ru.ifmo.chat.controller

import dto.MessageDto
import dto.SendMessageDto
import org.apache.logging.log4j.message.Message
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.ifmo.chat.service.MessageService

@RestController
@RequestMapping("/messages")
class MessageController @Autowired constructor(
    private val messageService: MessageService
) {
    @GetMapping("/")
    fun getMessages(@RequestHeader("Username") username: String): List<MessageDto> {
        return messageService.findMediaFileByUsername(username).map { message -> MessageDto(message.id, message.username, message.recipient, message.text, message.attachment, message.createdDate) }
    }

    @PostMapping("/")
    fun sendMessage(
        @RequestHeader("Username") username: String,
        @RequestBody sendMessageDto: SendMessageDto
    ): MessageDto {
        val message = messageService.sendMessage(username, sendMessageDto.recipient, sendMessageDto.text, sendMessageDto.attachment)
        return MessageDto(message.id, message.username, message.recipient, message.text, message.attachment, message.createdDate);
    }
}
