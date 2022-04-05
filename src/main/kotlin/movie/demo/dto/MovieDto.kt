package movie.demo.dto
import io.swagger.v3.oas.annotations.media.Schema
import movie.demo.web.rest.CreateMovieCommand

@Schema(description = "Model for a movie")
data class MovieDTO (
    val id: Int,
    override val name: String,
    override val rating: Double
    ): CreateMovieCommand(name, rating)