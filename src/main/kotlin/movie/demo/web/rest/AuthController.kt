package movie.demo.web.rest
/*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import movie.demo.dto.MovieDTO
import movie.demo.security.AuthenticationService
import movie.demo.service.MovieService
import movie.demo.shared.toLocation
import org.springdoc.api.OpenApiResourceNotFoundException
import org.springframework.http.HttpHeaders*/
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import movie.demo.entity.User
import movie.demo.repository.UserRepository
import movie.demo.shared.toLocation
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*
import java.util.*

const val EXPIRATION_TIME = 1209600000 // 2 weeks in ms
const val SECRET = "SomeSecretYouProbablyWantToMakeConfigurable"
const val AUTH_COOKIE = "AUTH_COOKIE"

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authManager: AuthenticationManager,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val userRepository: UserRepository
) {

    @PostMapping("/token")
    fun auth(@RequestBody cmd: AuthCommand) : ResponseEntity<String>{
        val user = userRepository.findByUsername(cmd.username)
//        if(cmd.password == null || passwordEncoder.encode(cmd.password) != user?.password)
//            return ResponseEntity.badRequest().body("Username or password is invalid.")

        val token = JWT.create()
            .withSubject(cmd.username)
            .withExpiresAt(Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .sign(Algorithm.HMAC512(SECRET))
        return ResponseEntity.ok(token)
    }

    @PostMapping("/register")
    fun register(cmd:CreateUserCommand) : ResponseEntity<User>{
        // When the register form is submitted, save the user information into the database.
        val user = User(0, cmd.username, passwordEncoder.encode(cmd.password))
        userRepository.save(user)
        return ResponseEntity.created(user.id.toLocation()).body(user)
    }
}

data class CreateUserCommand(val username:String, val password:String)
data class AuthCommand(val username:String, val password:String)