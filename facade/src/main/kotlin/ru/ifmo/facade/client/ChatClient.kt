package ru.ifmo.facade.client

import dto.MessageDto
import dto.SendMessageDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@FeignClient("chat-service")
interface ChatClient {
    @GetMapping("/messages")
    fun getMessages(): List<MessageDto>

    @PostMapping("/messages")
    fun sendMessage(@RequestBody sendMessageDto: SendMessageDto): MessageDto
}