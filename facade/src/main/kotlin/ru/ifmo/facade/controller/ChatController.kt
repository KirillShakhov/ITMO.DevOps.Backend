package ru.ifmo.facade.controller

import ru.ifmo.commons.dto.MessageDto
import ru.ifmo.commons.dto.SendMessageDto
import ru.ifmo.facade.client.ChatClient
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
class ChatController @Autowired constructor(
    private val chatClient: ChatClient
) {
    @GetMapping("/")
    fun getMessages(): List<MessageDto> {
        return chatClient.getMessages()
    }

    @PostMapping("/")
    fun sendMessage(@RequestBody sendMessageDto: SendMessageDto): MessageDto {
        return chatClient.sendMessage(sendMessageDto)
    }
}