package ru.ifmo.facade.security

import lombok.RequiredArgsConstructor
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
@RequiredArgsConstructor
class JwtAuthenticationFilter : OncePerRequestFilter() {
    private val jwtTokenProvider: JwtTokenProvider? = null
    private val jwtTokenUtils: JwtTokenUtils? = null

    companion object {
        private const val USERNAME_KEY = "Username"

        private const val TOKEN_PREFIX = "Bearer "
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = jwtTokenUtils!!.tokenFromRequest(request)

        if (token != null && request.getAttribute("username") == null && StringUtils.hasText(token) && jwtTokenProvider!!.validateToken(
                token
            )
        ) {
            val claims = jwtTokenProvider.extractAllClaims(token)
            val username = claims[USERNAME_KEY] as String?
            val authentication = UsernamePasswordAuthenticationToken(
                username, null, Collections.emptyList()
            )
            authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
            SecurityContextHolder.getContext().authentication = authentication
            request.setAttribute("Username", username)
        }
        filterChain.doFilter(request, response)
    }
}