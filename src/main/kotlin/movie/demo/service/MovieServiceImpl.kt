package movie.demo.service

import movie.demo.dto.MovieDTO
import movie.demo.entity.Movie
import movie.demo.mapper.MovieMapper
import movie.demo.repository.MovieRepository
import movie.demo.web.rest.CreateMovieCommand
import org.springframework.stereotype.Service

@Service
class MovieServiceImpl(
    private val _movieRepository: MovieRepository,
    private val _movieMapper: MovieMapper
) : MovieService {
    override fun getMovies(): List<MovieDTO> {
        var movies = _movieRepository.getAllMovies()
        return movies.map { _movieMapper.fromEntity(it) }
    }

    override fun createMovie(createMovieCommand: CreateMovieCommand): MovieDTO {
        val movie = Movie(0, createMovieCommand.name, createMovieCommand.rating)
        _movieRepository.save(movie)
        return _movieMapper.fromEntity(movie)
    }
}