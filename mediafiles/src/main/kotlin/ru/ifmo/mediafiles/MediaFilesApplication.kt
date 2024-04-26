package ru.ifmo.mediafiles

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.config.EnableMongoAuditing

@EnableMongoAuditing
@SpringBootApplication
class MediaFilesApplication

fun main(args: Array<String>) {
    runApplication<MediaFilesApplication>(*args)
}
