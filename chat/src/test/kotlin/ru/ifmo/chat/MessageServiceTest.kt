package ru.ifmo.chat

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import ru.ifmo.chat.model.Message
import ru.ifmo.chat.repository.MessageRepository
import ru.ifmo.chat.service.MessageService
import java.util.*

@SpringBootTest
class MessageServiceTest {

    @Autowired
    private lateinit var messageService: MessageService

    @MockBean
    private lateinit var messageRepository: MessageRepository

    @Test
    fun `should find message by id`() {
        val message = Message("1", "testuser", "recipient1", "Hello", null, Date())

        Mockito.`when`(messageRepository.findById("1")).thenReturn(Optional.of(message))

        val result = messageService.findById("1")

        assertTrue(result.isPresent)
        assertEquals("1", result.get().id)
        assertEquals("testuser", result.get().username)
        assertEquals("recipient1", result.get().recipient)
        assertEquals("Hello", result.get().text)
    }

    @Test
    fun `should return empty when message not found`() {
        Mockito.`when`(messageRepository.findById("1")).thenReturn(Optional.empty())

        val result = messageService.findById("1")

        assertTrue(result.isEmpty)
    }
}
