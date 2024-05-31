package ru.ifmo.facade.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.ifmo.commons.dto.ApiResponse
import ru.ifmo.commons.dto.AuthenticationResponse
import ru.ifmo.commons.dto.LoginRequest
import ru.ifmo.commons.dto.RegisterDto


@FeignClient("auth", url = "http://auth-service:9992")
interface AuthClient {
    @PostMapping("/signin")
    fun authenticateUser(@RequestBody loginRequest: LoginRequest): AuthenticationResponse

    @PostMapping("/signup")
    fun registerUser(@RequestBody registerDto: RegisterDto): ApiResponse
}