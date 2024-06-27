package ru.ifmo.chat.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import ru.ifmo.chat.service.MessageService
import ru.ifmo.commons.dto.MessageDto
import ru.ifmo.commons.dto.SendMessageDto

@RestController
@RequestMapping("/messages")
class MessageController @Autowired constructor(
    private val messageService: MessageService
) {
    @GetMapping("/")
    fun getMessages(@RequestHeader username: String): List<MessageDto> {
        return messageService.findByUsername(username).map { message -> MessageDto(message.id, message.username, message.recipient, message.text, message.attachment, message.createdDate) }
    }

    @PostMapping("/")
    fun sendMessage(
        @RequestHeader username: String,
        @RequestBody sendMessageDto: SendMessageDto
    ): MessageDto {
        val message = messageService.sendMessage(username, sendMessageDto.recipient, sendMessageDto.text, sendMessageDto.attachment)
        return MessageDto(message.id, message.username, message.recipient, message.text, message.attachment, message.createdDate)
    }

    @GetMapping("/{id}")
    fun getMessageById(@PathVariable id: String, @RequestHeader username: String): String {
        val message = messageService.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found") }
        return "Message from ${message.username} to ${message.recipient}: ${message.text}"
    }
}
