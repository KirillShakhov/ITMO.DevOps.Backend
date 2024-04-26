package ru.ifmo.mediafiles.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import ru.ifmo.mediafiles.model.MediaFile

@Repository
interface MediaFileRepository : MongoRepository<MediaFile, String> {
    fun findUserByUsername(username: String): MediaFile
    fun save(mediaFile: MediaFile): MediaFile
}