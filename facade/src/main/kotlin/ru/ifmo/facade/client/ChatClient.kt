package ru.ifmo.facade.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@FeignClient("chat-service")
interface ChatClient {
    @GetMapping("/messages")
    fun getMessages(): List<Message>

    @PostMapping("/messages")
    fun token(@RequestBody tokenDto: TokenDto?): TokenDto?

    @PostMapping("/api/v1/auth/refresh")
    fun refresh(@RequestBody tokenDto: TokenDto?): TokenDto?


    @PostMapping("/api/v1/auth/login")
    fun login(credentialsDto: CredentialsDto): TokenDto?

    @PostMapping("/api/v1/users/add")
    fun registerNewUser(@Valid @RequestBody userDto: UserDto?): String?

    @PostMapping("/api/v1/users/change-role")
    fun changeRole(@RequestParam username: String?): String?

    @GetMapping("/api/v1/users/page/{number}")
    fun getAllUsers(@PathVariable number: Int?): PageDto?
}