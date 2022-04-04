package movie.demo.shared

import org.springframework.http.HttpStatus

class ProblemDetail(private val _message: String?, val status: HttpStatus = HttpStatus.BAD_REQUEST) {
    val message: String
        get() = _message ?: "Something went wrong"
}