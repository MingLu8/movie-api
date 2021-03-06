package movie.demo.security


import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.web.util.WebUtils
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

const val EXPIRATION_TIME = 1209600000 // 2 weeks in ms
const val SECRET = "SomeSecretYouProbablyWantToMakeConfigurable"
const val AUTH_COOKIE = "AUTH_COOKIE"

class JWTAuthorizationFilter @Autowired constructor(authManager: AuthenticationManager)
    : BasicAuthenticationFilter(authManager) {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {

        // Get the authentication cookie. This cookie holds the JWT.
        var accessToken = request.getHeader("Authorization")?.replace("Bearer ", "", true)
        //?: WebUtils.getCookie(request, AUTH_COOKIE)?.value?.trim()
        // Get the authentication cookie. This cookie holds the JWT.


        if (accessToken == null || accessToken.isEmpty()) {
            // If there is no cookie, the user is not authenticated. Continue the filter chain.
            chain.doFilter(request, response)
            return
        }

        // There is an authentication cookie. Verify the JWT and and set the authentication token on
        // springs security context. This lets spring know who the logged in user is.
        val authenticationToken = getAuthenticationToken(accessToken)
        SecurityContextHolder.getContext().authentication = authenticationToken
        chain.doFilter(request, response)
    }

    private fun getAuthenticationToken(token: String): UsernamePasswordAuthenticationToken? {

        // Parse and verify the provided token.
        val parsedToken = JWT.require(Algorithm.HMAC512(SECRET.toByteArray()))
            .build()
            .verify(token)
            .subject ?: return null

        return UsernamePasswordAuthenticationToken(parsedToken, null, listOf())
    }
}
