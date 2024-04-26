package ru.ifmo.facade.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*
import ru.ifmo.ru.ifmo.commons.dto.MessageDto
import ru.ifmo.ru.ifmo.commons.dto.SendMessageDto


@FeignClient("chat")
interface ChatClient {
    @GetMapping("/messages")
    fun getMessages(): List<MessageDto>

    @PostMapping("/messages")
    fun sendMessage(@RequestBody sendMessageDto: SendMessageDto): MessageDto
}