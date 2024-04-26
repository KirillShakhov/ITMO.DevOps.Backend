package ru.ifmo.facade.client

import ru.ifmo.commons.dto.MessageDto
import ru.ifmo.commons.dto.SendMessageDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@FeignClient("chat")
interface ChatClient {
    @GetMapping("/messages")
    fun getMessages(): List<MessageDto>

    @PostMapping("/messages")
    fun sendMessage(@RequestBody sendMessageDto: SendMessageDto): MessageDto
}