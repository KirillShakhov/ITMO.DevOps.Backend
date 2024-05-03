package ru.ifmo.facade.security

import org.springframework.lang.NonNull
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import javax.servlet.http.HttpServletRequest


@Component
class JwtTokenUtils {
    fun tokenFromRequest(@NonNull request: HttpServletRequest): String? {
        val header: String = request.getHeader(AUTHORIZATION) ?: return null
        if (!StringUtils.hasText(header) || !header.startsWith(TOKEN_PREFIX)) return null
        return header.substring(TOKEN_PREFIX.length)
    }

    companion object {
        private const val AUTHORIZATION = "Authorization"
        private const val TOKEN_PREFIX = "Bearer "
    }
}