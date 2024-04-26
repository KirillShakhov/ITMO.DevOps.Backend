package ru.ifmo.mediafiles.service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.ifmo.mediafiles.model.MediaFile
import ru.ifmo.mediafiles.repository.MediaFileRepository
import ru.ifmo.commons.dto.MediaType


@Service
class MediaFileService @Autowired constructor(
    private val mediaFileRepository: MediaFileRepository
) {
    fun findMediaFileByUsername(username: String): MediaFile {
        return mediaFileRepository.findUserByUsername(username)
    }

    fun saveMediaFile(username: String, type: MediaType, base64: String): MediaFile {
        val mediaFile = MediaFile(null, username, type, base64, null);
        return mediaFileRepository.save(mediaFile);
    }
}