package movie.demo.web.rest

import movie.demo.dto.MovieDTO
import movie.demo.service.MovieService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/movies")
class MovieResource(
    private val _movieService : MovieService
) {
    @GetMapping
    fun getMovies() : ResponseEntity<List<MovieDTO>> = ResponseEntity.ok(_movieService.getMovies())

    @PostMapping
    fun createMovie(@RequestBody movieDTO: MovieDTO) : ResponseEntity<MovieDTO>{
        var id = _movieService.createMovie(movieDTO)
        return ResponseEntity.created(URI.create(id.toString())).body(movieDTO)
    }
}