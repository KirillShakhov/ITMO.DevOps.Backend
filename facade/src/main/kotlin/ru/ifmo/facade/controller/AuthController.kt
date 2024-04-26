package ru.ifmo.facade.controller

import dto.*
import ru.ifmo.facade.client.ChatClient
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import ru.ifmo.commons.dto.LoginRequest
import ru.ifmo.commons.dto.RegisterDto
import ru.ifmo.facade.client.AuthClient


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