package ru.ifmo.auth.controller
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import ru.ifmo.auth.model.UserAuth
import ru.ifmo.auth.repository.UserRepository
import ru.ifmo.auth.security.JwtUtil
import ru.ifmo.auth.service.AuthenticationService
import ru.ifmo.commons.dto.ApiResponse
import ru.ifmo.commons.dto.AuthenticationResponse
import ru.ifmo.commons.dto.LoginRequest
import ru.ifmo.commons.dto.RegisterDto
import javax.servlet.http.HttpServletResponse

@RestController
class AuthenticationController {
    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    private lateinit var userDetailsService: AuthenticationService

    @Autowired
    private lateinit var jwtUtil: JwtUtil

    @Autowired
    private lateinit var userRepository: UserRepository

    @GetMapping("/hello")
    fun hello(): String {
        return "Harfan here"
    }

    @GetMapping("/checkUser")
    fun checkUser(): String {
        val authentication =
            SecurityContextHolder.getContext().authentication
        return authentication.name
    }

    @PostMapping("/authenticate")
    fun authenticate(@RequestBody authenticationRequest: LoginRequest): ResponseEntity<*> {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authenticationRequest.username,
                authenticationRequest.password
            )
        )
        val userDetails: UserDetails = authenticationRequest.username.let { userDetailsService.loadUserByUsername(it) }!!
        val jwt = jwtUtil.generateToken(userDetails)
        return ResponseEntity.ok<Any>(AuthenticationResponse(jwt))
    }

    @ResponseBody
    @PostMapping("/signin")
    fun authenticateUser(@RequestBody loginRequest: LoginRequest): AuthenticationResponse {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                loginRequest.username,
                loginRequest.password
            )
        )
        SecurityContextHolder.getContext().authentication = authentication
        val userDetails: UserDetails = userDetailsService.loadUserByUsername(loginRequest.username)!!
        val jwt = jwtUtil.generateToken(userDetails)
        return AuthenticationResponse(loginRequest.username, jwt)
    }

    @ResponseBody
    @PostMapping("/signup")
    fun registerUser(@RequestBody registerDto: RegisterDto): ApiResponse {
        // Creating user's account
        if (userRepository.existsByUsername(registerDto.username)){
            return ApiResponse(false, "User is already registered")
        }
        val jwtUser = UserAuth(null, registerDto.username, passwordEncoder.encode(registerDto.password))

        println("jwtUser >> " + jwtUser.username)
        jwtUser.let { userRepository.save(it) }
        return ApiResponse(true, "User registered successfully")
    }

    @PostMapping("/logout-user")
    fun logout(@RequestHeader("Authorization") authorizationHeader: String): ResponseEntity<String> {
        val jwtToken = authorizationHeader.substring(7)
        jwtUtil.invalidateToken(jwtToken)
        return ResponseEntity.ok("Logged out successfully.")
    }
}