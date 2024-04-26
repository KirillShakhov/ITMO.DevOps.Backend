package ru.ifmo.facade.client

import ru.ifmo.commons.dto.LoginRequest
import ru.ifmo.commons.dto.MessageDto
import ru.ifmo.commons.dto.RegisterDto
import ru.ifmo.commons.dto.SendMessageDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@FeignClient("auth")
interface AuthClient {
    @PostMapping("/signin")
    fun authenticateUser(@RequestBody loginRequest: LoginRequest): ResponseEntity<*>

    @PostMapping("/signup")
    fun registerUser(@RequestBody registerDto: RegisterDto): ResponseEntity<*>
}