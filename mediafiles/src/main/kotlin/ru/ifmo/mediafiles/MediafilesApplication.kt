package ru.ifmo.mediafiles

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MediafilesApplication

fun main(args: Array<String>) {
    runApplication<MediafilesApplication>(*args)
}
