package ru.ifmo.chat

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import ru.ifmo.chat.controller.MessageController
import ru.ifmo.chat.service.MessageService
import ru.ifmo.commons.dto.SendMessageDto
import ru.ifmo.chat.model.Message
import java.util.*

@WebMvcTest(MessageController::class)
class MessageControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var messageService: MessageService

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `should get messages`() {
        val username = "testuser"
        val messages = listOf(
            Message("1", username, "recipient1", "Hello", null, Date()),
            Message("2", username, "recipient2", "Hi", null, Date())
        )

        Mockito.`when`(messageService.findByUsername(username)).thenReturn(messages)

        mockMvc.perform(get("/messages/")
            .header("username", username))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].id").value(messages[0].id))
            .andExpect(jsonPath("$[0].username").value(messages[0].username))
            .andExpect(jsonPath("$[0].recipient").value(messages[0].recipient))
            .andExpect(jsonPath("$[0].text").value(messages[0].text))
            .andExpect(jsonPath("$[1].id").value(messages[1].id))
            .andExpect(jsonPath("$[1].username").value(messages[1].username))
            .andExpect(jsonPath("$[1].recipient").value(messages[1].recipient))
            .andExpect(jsonPath("$[1].text").value(messages[1].text))
    }

    @Test
    fun `should send message`() {
        val username = "testuser"
        val sendMessageDto = SendMessageDto("recipient1", "Hello", null)
        val message = Message("1", username, sendMessageDto.recipient, sendMessageDto.text, sendMessageDto.attachment, Date())

        Mockito.`when`(messageService.sendMessage(username, sendMessageDto.recipient, sendMessageDto.text, sendMessageDto.attachment)).thenReturn(message)

        mockMvc.perform(post("/messages/")
            .header("username", username)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(sendMessageDto)))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(message.id))
            .andExpect(jsonPath("$.username").value(message.username))
            .andExpect(jsonPath("$.recipient").value(message.recipient))
            .andExpect(jsonPath("$.text").value(message.text))
    }
}
