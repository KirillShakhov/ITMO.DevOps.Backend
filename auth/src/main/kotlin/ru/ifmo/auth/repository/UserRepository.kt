package ru.ifmo.auth.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import ru.ifmo.auth.model.UserAuth

@Repository
interface UserRepository : MongoRepository<UserAuth, String> {
    fun findUserByEmail(email: String): UserAuth
    fun save(userAuth: UserAuth)
}