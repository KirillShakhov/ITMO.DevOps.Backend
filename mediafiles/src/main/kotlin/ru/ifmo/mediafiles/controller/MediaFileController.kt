package ru.ifmo.mediafiles.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.ifmo.mediafiles.service.MediaFileService
import ru.ifmo.ru.ifmo.commons.dto.MediaFileUpload

@RestController
@RequestMapping("/mediafiles")
class MediaFileController @Autowired constructor(
    private val mediaFileService: MediaFileService
) {
    @GetMapping("/")
    fun getMediaFileByUsername(@RequestHeader("Username") username: String): ResponseEntity<*> {
        return ResponseEntity.ok(mediaFileService.findMediaFileByUsername(username))
    }

    @PostMapping("/")
    fun saveMediaFile(@RequestHeader("Username") username: String, @RequestBody mediaFileUpload: MediaFileUpload): ResponseEntity<*> {
        val savedMediaFile = mediaFileService.saveMediaFile(username, mediaFileUpload.type, mediaFileUpload.base64)
        return ResponseEntity.ok(savedMediaFile)
    }
}