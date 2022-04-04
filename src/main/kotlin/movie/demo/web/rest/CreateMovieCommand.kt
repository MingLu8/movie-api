package movie.demo.web.rest

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Model for a movie")
data class CreateMovieCommand(
    val name:String,

    @field:Schema(
        description = "A number to rate the movie",
        example = "3.5",
        type = "double",
        minimum = "0",
        maximum = "5.0"
    )
    val rating:Double)
