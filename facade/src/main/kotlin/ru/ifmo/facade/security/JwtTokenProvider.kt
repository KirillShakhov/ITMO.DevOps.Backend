package ru.ifmo.facade.security

import io.jsonwebtoken.*
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.lang.NonNull
import org.springframework.stereotype.Component
import java.security.SignatureException


@Component
@RequiredArgsConstructor
class JwtTokenProvider {
    @Value("\${jwt.secret}")
    private val jwtSecret: String? = null

    fun validateToken(@NonNull token: String?): Boolean {
        try {
            extractAllClaims(token)
            return true
        } catch (exception: SignatureException) {
            System.out.println("Invalid JWT signature: ${exception.message}")
        } catch (exception: MalformedJwtException) {
            System.out.println("Invalid JWT token: ${exception.message}")
        } catch (exception: ExpiredJwtException) {
            System.out.println("JWT token is expired: ${exception.message}")
        } catch (exception: UnsupportedJwtException) {
            System.out.println("JWT token is unsupported: ${exception.message}")
        } catch (exception: IllegalArgumentException) {
            System.out.println("JWT claims string is empty: ${exception.message}")
        }
        return false
    }

    fun extractAllClaims(@NonNull token: String?): Claims {
        return Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .getBody()
    }
}