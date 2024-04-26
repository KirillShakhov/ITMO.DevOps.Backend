package ru.ifmo.facade

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class FacadeApplication

fun main(args: Array<String>) {
	runApplication<FacadeApplication>(*args)
}
