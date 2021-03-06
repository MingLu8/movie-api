package movie.demo.mapper

import movie.demo.dto.MovieDTO
import movie.demo.entity.Movie
import movie.demo.web.rest.CreateMovieCommand
import org.springframework.stereotype.Service

@Service
class MovieMapper : Mapper<MovieDTO, Movie> {
    override fun fromEntity(entity: Movie): MovieDTO = MovieDTO(
        entity.id,
        entity.name,
        entity.rating
    )

    override fun toEntity(domain: MovieDTO): Movie = Movie(
        domain.id,
        domain.name,
        domain.rating
    )
}