package movie.demo.web.rest

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import movie.demo.dto.MovieDTO
import movie.demo.security.AuthenticationService
import movie.demo.service.MovieService
import movie.demo.shared.toLocation
import org.springdoc.api.OpenApiResourceNotFoundException
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/movies")
class MovieResource(
    private val _movieService : MovieService,
    private val _request : HttpServletRequest,
    private val _authService : AuthenticationService
) {
    @GetMapping("{id}")
    fun getMovieById(@PathVariable id:Int) : ResponseEntity<MovieDTO>{

        //1. validate auth token
        val oAuthToken: String = _request.getHeader(HttpHeaders.AUTHORIZATION)
        _authService.validateAuthorisation(oAuthToken)

        val movie = _movieService.getMovieById(id) ?: throw OpenApiResourceNotFoundException("Movie not found for Id: $id.")
        return ResponseEntity.ok(movie)
    }


    @GetMapping
    fun getMovies() : ResponseEntity<List<MovieDTO>> = ResponseEntity.ok(_movieService.getMovies())

    @Operation(summary = "Creates a movie rating", description = "Returns 201 if successful")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "Successful Operation"),
            ApiResponse(responseCode = "401", description = "Invalid movie model"),
        ]
    )
    @PostMapping
    fun createMovie(@RequestBody cmd: CreateMovieCommand) : ResponseEntity<MovieDTO>{
        val dto = _movieService.createMovie(cmd)
        return ResponseEntity.created(dto.id.toLocation()).body(dto)
    }

    @PutMapping("{id}")
    fun createMovie(@PathVariable id:Int, @RequestBody cmd: UpdateMovieCommand) : ResponseEntity<MovieDTO>{
        val dto = _movieService.updateMovie(id, cmd)
        return ResponseEntity.accepted().body(dto)
    }

    @DeleteMapping("{id}")
    fun deleteMovie(@PathVariable id:Int) : ResponseEntity<String?>{
        _movieService.deleteMovie(id)
        return ResponseEntity.ok().body(null)
    }
}