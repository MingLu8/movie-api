package movie.demo.web.rest

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Model for create a movie")
open class CreateMovieCommand(
    open val name:String,
    @field:Schema(
        description = "A number to rate the movie",
        example = "3.5",
        type = "double",
        minimum = "0",
        maximum = "5.0"
    )
    open val rating:Double)


@Schema(description = "Model for update a movie")
open class UpdateMovieCommand(
    override val name:String,
    @field:Schema(
        description = "A number to rate the movie",
        example = "3.5",
        type = "double",
        minimum = "0",
        maximum = "5.0"
    )
    override val rating:Double) : CreateMovieCommand(name, rating)
