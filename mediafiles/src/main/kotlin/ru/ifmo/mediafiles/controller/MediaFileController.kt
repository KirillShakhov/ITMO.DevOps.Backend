package ru.ifmo.mediafiles.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.ifmo.mediafiles.dto.MediaFileUpload
import ru.ifmo.mediafiles.service.MediaFileService

@RestController
@RequestMapping("/mediafiles")
class MediaFileController @Autowired constructor(
    private val mediaFileService: MediaFileService
) {
    @GetMapping("/user/{username}")
    fun getMediaFileByUsername(@PathVariable username: String): ResponseEntity<*> {
        return ResponseEntity.ok(mediaFileService.findMediaFileByUsername(username))
    }

    @PostMapping("/")
    fun saveMediaFile(@RequestBody mediaFileUpload: MediaFileUpload): ResponseEntity<*> {
        val savedMediaFile = mediaFileService.saveMediaFile(mediaFileUpload)
        return ResponseEntity.ok(savedMediaFile)
    }
}