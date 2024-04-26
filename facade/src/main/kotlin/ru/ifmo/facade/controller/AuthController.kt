package ru.ifmo.facade.controller

import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.ifmo.facade.client.AuthClient
import ru.ifmo.commons.dto.LoginRequest
import ru.ifmo.commons.dto.RegisterDto


@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
class AuthController @Autowired constructor(
    private val authClient: AuthClient
) {
    @PostMapping("/signin")
    fun authenticateUser(@RequestBody loginRequest: LoginRequest): ResponseEntity<*> {
        return authClient.authenticateUser(loginRequest)
    }


    @PostMapping("/signup")
    fun registerUser(@RequestBody registerDto: RegisterDto): ResponseEntity<*> {
        return authClient.registerUser(registerDto)
    }
}