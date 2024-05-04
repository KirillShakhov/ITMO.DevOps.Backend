package ru.ifmo.facade.config

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.security.core.context.SecurityContextHolder


class FeignConfig : RequestInterceptor {
    override fun apply(template: RequestTemplate?) {
        val username = SecurityContextHolder.getContext().authentication.principal.toString()
        template?.header("username", username)
    }
}
