package movie.demo.web.rest

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import movie.demo.dto.MovieDTO
import movie.demo.exception.MovieException
import movie.demo.service.MovieService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/movies")
class MovieResource(
    private val _movieService : MovieService
) {
    @GetMapping("{id:Int}")
    fun getMovieById(id:Int) : ResponseEntity<MovieDTO>{
        throw MovieException("Not Implemented. $id")
    }


    @GetMapping
    fun getMovies() : ResponseEntity<List<MovieDTO>> = ResponseEntity.ok(_movieService.getMovies())

    @Operation(summary = "Sets a price for a chosen car", description = "Returns 202 if successful")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "202", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "Such a car does not exist"),
        ]
    )
    @PostMapping
    fun createMovie(@RequestBody movieDTO: MovieDTO) : ResponseEntity<MovieDTO>{
        var id = _movieService.createMovie(movieDTO)
        return ResponseEntity.created(URI.create(id.toString())).body(movieDTO)
    }
}