package ru.ifmo.facade.security

import org.springframework.lang.NonNull
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import javax.servlet.http.HttpServletRequest


@Component
class JwtTokenUtils {
    fun tokenFromRequest(@NonNull request: HttpServletRequest): String? {
        val header: String = request.getHeader(AUTHORIZATION)

        if (StringUtils.hasText(header) && header.startsWith(TOKEN_PREFIX)) {
            return header.substring(TOKEN_PREFIX.length)
        }

        return null
    }

    companion object {
        private const val AUTHORIZATION = "Authorization"
        private const val TOKEN_PREFIX = "Bearer "
    }
}