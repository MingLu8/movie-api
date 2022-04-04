package movie.demo.dto
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Model for a movie")
data class MovieDTO (
    val id: Int,
    var name: String,

    @field:Schema(
        description = "A number to rate the movie",
        example = "3.5",
        type = "double",
        minimum = "0",
        maximum = "5.0"
    )
    var rating : Double
)