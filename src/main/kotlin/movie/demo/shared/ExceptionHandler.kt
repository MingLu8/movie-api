package movie.demo.shared

import movie.demo.exception.MovieException
import org.springdoc.api.OpenApiResourceNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler
    fun generalExceptionHandler(exception: Exception): ResponseEntity<ProblemDetail>{
        var error = ProblemDetail(exception.message, HttpStatus.INTERNAL_SERVER_ERROR)
        return ResponseEntity(error, error.status)
    }

    @ExceptionHandler
    fun resourceNotFoundExceptionHandler(exception: OpenApiResourceNotFoundException): ResponseEntity<ProblemDetail>{
        var error = ProblemDetail(exception.message, HttpStatus.NOT_FOUND)
        return ResponseEntity(error, error.status)
    }

    @ExceptionHandler
    fun movieExceptionHandler(exception: MovieException): ResponseEntity<ProblemDetail>{
        var error = ProblemDetail(exception.message)
        return ResponseEntity(error, error.status)
    }
}