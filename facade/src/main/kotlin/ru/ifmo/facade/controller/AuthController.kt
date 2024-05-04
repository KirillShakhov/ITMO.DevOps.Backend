package ru.ifmo.facade.controller

import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.ifmo.commons.dto.ApiResponse
import ru.ifmo.commons.dto.AuthenticationResponse
import ru.ifmo.facade.client.AuthClient
import ru.ifmo.commons.dto.LoginRequest
import ru.ifmo.commons.dto.RegisterDto


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
class AuthController @Autowired constructor(
    private val authClient: AuthClient
) {
    @ResponseBody
    @PostMapping("/signin")
    fun authenticateUser(@RequestBody loginRequest: LoginRequest): AuthenticationResponse {
        return authClient.authenticateUser(loginRequest)
    }

    @ResponseBody
    @PostMapping("/signup")
    fun registerUser(@RequestBody registerDto: RegisterDto): ApiResponse {
        return authClient.registerUser(registerDto)
    }
}