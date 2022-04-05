package movie.demo.web.rest

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import movie.demo.dto.MovieDTO
import movie.demo.exception.MovieException
import movie.demo.service.MovieService
import movie.demo.shared.toLocation
import org.springdoc.api.OpenApiResourceNotFoundException
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/movies")
class MovieResource(
    private val _movieService : MovieService
) {
    @GetMapping("{id}")
    fun getMovieById(@PathVariable id:Int) : ResponseEntity<MovieDTO>{
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