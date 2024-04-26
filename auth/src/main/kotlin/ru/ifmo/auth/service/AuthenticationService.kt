package ru.ifmo.auth.service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ru.ifmo.auth.model.UserAuth
import ru.ifmo.auth.repository.UserRepository


@Service
class AuthenticationService : UserDetailsService {
    @Autowired
    var jwtUserRepository: UserRepository? = null

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails? {
        val user: UserAuth = jwtUserRepository!!.findUserByUsername(username)
        return org.springframework.security.core.userdetails.User(user.username, user.password, ArrayList())
    }
}