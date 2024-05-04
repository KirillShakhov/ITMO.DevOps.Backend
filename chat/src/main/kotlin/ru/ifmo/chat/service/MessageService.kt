package ru.ifmo.chat.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.ifmo.chat.model.Message
import ru.ifmo.chat.repository.MessageRepository
import java.util.Date

@Service
class MessageService @Autowired constructor(
    private val messageRepository: MessageRepository
) {
    fun findByUsername(username: String): List<Message> {
        return messageRepository.findByRecipientOrUsername(username, username)
    }

    fun sendMessage(username: String, recipient: String, text: String, attachment: Int?): Message {
        val message = Message(null, username, recipient, text, attachment, Date());
        return messageRepository.save(message);
    }
}