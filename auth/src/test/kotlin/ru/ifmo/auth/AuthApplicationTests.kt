package ru.ifmo.auth

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import ru.ifmo.auth.controller.AuthenticationController
import ru.ifmo.auth.repository.UserRepository
import ru.ifmo.auth.security.JwtUtil
import ru.ifmo.auth.service.AuthenticationService
import ru.ifmo.commons.dto.RegisterDto

@WebMvcTest(AuthenticationController::class)
class AuthenticationControllerTests {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var authenticationManager: AuthenticationManager

    @MockBean
    private lateinit var userDetailsService: AuthenticationService

    @MockBean
    private lateinit var jwtUtil: JwtUtil

    @MockBean
    private lateinit var userRepository: UserRepository

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
        // Mock the authentication object for use with SecurityContextHolder if needed
    }

    @Test
    @WithMockUser(username = "user")
    fun `Test checkUser endpoint`() {
        mockMvc.perform(get("/checkUser"))
            .andExpect(status().isOk)
            .andExpect(content().string("user"))
    }

    @Test
    fun `Test signup endpoint`() {
        val registerDto = RegisterDto("newuser", "newpassword")

        mockMvc.perform(post("/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(registerDto)))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.message").value("User registered successfully"))
    }
}
