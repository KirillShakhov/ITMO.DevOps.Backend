package ru.ifmo.mediafiles.service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.annotation.CreatedDate
import org.springframework.stereotype.Service
import ru.ifmo.mediafiles.dto.MediaFileUpload
import ru.ifmo.mediafiles.model.MediaFile
import ru.ifmo.mediafiles.model.MediaType
import ru.ifmo.mediafiles.repository.MediaFileRepository
import java.util.Date


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