package ru.ifmo.mediafiles.service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.annotation.CreatedDate
import org.springframework.stereotype.Service
import ru.ifmo.mediafiles.dto.MediaFileUpload
import ru.ifmo.mediafiles.model.MediaFile
import ru.ifmo.mediafiles.repository.MediaFileRepository
import java.util.Date


@Service
class MediaFileService @Autowired constructor(
    private val mediaFileRepository: MediaFileRepository
) {
    fun findMediaFileByUsername(username: String): MediaFile {
        return mediaFileRepository.findUserByUsername(username)
    }

    fun saveMediaFile(mediaFileUpload: MediaFileUpload): MediaFile {
        val mediaFile = MediaFile(null, mediaFileUpload.username, mediaFileUpload.type, mediaFileUpload.base64, null);
        return mediaFileRepository.save(mediaFile);
    }
}