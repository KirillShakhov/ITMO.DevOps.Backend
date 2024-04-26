package ru.ifmo.facade.controller

import ru.ifmo.facade.client.ChatClient
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
class ChatController {
    private val chatClient: ChatClient? = null

    @PostMapping("/login")
    fun login(@Valid @RequestBody credentialsDto: CredentialsDto?): TokenDto {
        return authClient.login(credentialsDto)
    }

    @PostMapping("/validateToken")
    fun validateToken(@RequestParam token: String?): Boolean {
        return authClient.validateToken(token)
    }

    @PostMapping("/token")
    fun token(@RequestBody tokenDto: TokenDto?): TokenDto {
        return chatClient.token(tokenDto)
    }

    @PostMapping("/refresh")
    fun refresh(@Valid @RequestBody tokenDto: TokenDto?): TokenDto {
        return authClient.refresh(tokenDto)
    }
}