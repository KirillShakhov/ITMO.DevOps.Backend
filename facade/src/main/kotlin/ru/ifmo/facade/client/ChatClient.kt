package ru.ifmo.facade.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*
import ru.ifmo.commons.dto.MessageDto
import ru.ifmo.commons.dto.SendMessageDto


@FeignClient("chat")
interface ChatClient {
    @GetMapping("/messages/")
    fun getMessages(@RequestHeader("Username") username: String): List<MessageDto>

    @PostMapping("/messages/")
    fun sendMessage(@RequestHeader("Username") username: String, @RequestBody sendMessageDto: SendMessageDto): MessageDto
}