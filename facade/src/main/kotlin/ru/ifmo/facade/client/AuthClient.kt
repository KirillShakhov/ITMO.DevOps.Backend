package ru.ifmo.facade.client

import dto.LoginRequest
import dto.MessageDto
import dto.RegisterDto
import dto.SendMessageDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@FeignClient("auth-service")
interface AuthClient {
    @PostMapping("/signin")
    fun authenticateUser(@RequestBody loginRequest: LoginRequest): ResponseEntity<*>

    @PostMapping("/signup")
    fun registerUser(@RequestBody registerDto: RegisterDto): ResponseEntity<*>
}