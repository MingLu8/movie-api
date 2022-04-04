package movie.demo.service

import movie.demo.dto.MovieDTO
import movie.demo.mapper.MovieMapper
import movie.demo.repository.MovieRepository
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

    override fun createMovie(movieDTO: MovieDTO): Int {
        val movie = _movieMapper.toEntity(movieDTO)
        _movieRepository.save(movie)
        return movie.id
    }
}