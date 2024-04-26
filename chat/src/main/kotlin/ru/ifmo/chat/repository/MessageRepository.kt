package ru.ifmo.chat.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import ru.ifmo.chat.model.Message

@Repository
interface MessageRepository : MongoRepository<Message, String> {
    fun findUserByUsername(username: String): List<Message>
    fun save(message: Message): Message
}