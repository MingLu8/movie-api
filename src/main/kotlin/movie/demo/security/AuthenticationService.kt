package movie.demo.security

import movie.demo.exception.AuthenticationException
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils

interface AuthenticationService {
    /**
     * Method will validate Token else throw exception in case of invalid token
     *
     * @param authToken
     * @throws AuthenticationException
     */
    @Throws(AuthenticationException::class)
    fun validateAuthorisation(authToken: String?)
}
/**
 * @author sourabh
 * @implNote Implementation class for Authentication Service
 */
@Service
class AuthenticationServiceImpl : AuthenticationService {
    /**
     * Method will validate Token else throw exception in case of invalid token
     *
     * @param authToken
     * @throws AuthenticationException
     */
    @Throws(AuthenticationException::class)
    override fun validateAuthorisation(authToken: String?) {
        if (StringUtils.isEmpty(authToken)) throw AuthenticationException("Auth token is not set. Please generate and set auth token") else if (authToken!!.contains(
                "invalid"
            )
        ) throw AuthenticationException("Invalid oAuth Token. Please generate a new one and use") else return  //to do actual 3rd party call to validate token
    }
}