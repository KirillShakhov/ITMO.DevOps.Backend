package ru.ifmo.facade.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.ifmo.commons.dto.LoginRequest
import ru.ifmo.commons.dto.RegisterDto


@FeignClient("auth")
interface AuthClient {
    @PostMapping("/signin")
    fun authenticateUser(@RequestBody loginRequest: LoginRequest): ResponseEntity<*>

    @PostMapping("/signup")
    fun registerUser(@RequestBody registerDto: RegisterDto): ResponseEntity<*>
}