package movie.demo.service

import movie.demo.dto.MovieDTO
import movie.demo.entity.Movie
import movie.demo.mapper.MovieMapper
import movie.demo.repository.MovieRepository
import movie.demo.web.rest.CreateMovieCommand
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Service
import java.sql.ResultSet

class MovieRowMapper : RowMapper<Movie> {
    override fun mapRow(rs : ResultSet, rowNum: Int) : Movie {
        val movie = Movie(rs.getInt("ID"), rs.getString("NAME"), rs.getDouble("RATING"))
        return movie
    }
}
@Service
class MovieServiceImpl(
    private val _movieRepository: MovieRepository,
    private val _db: Database,
    private val _movieMapper: MovieMapper
) : MovieService {
    override fun getMovies(): List<MovieDTO> {
        val id = 1
        val m = _db.query("Select * from Movie where id=?", arrayOf(id), MovieRowMapper())
        var movies = _movieRepository.getAllMovies()
        return movies.map { _movieMapper.fromEntity(it) }
    }

    override fun createMovie(createMovieCommand: CreateMovieCommand): MovieDTO {
        val movie = Movie(0, createMovieCommand.name, createMovieCommand.rating)
        _movieRepository.save(movie)
        return _movieMapper.fromEntity(movie)
    }

    override fun getMovieById(id:Int): MovieDTO? {
        val movies = _db.query("Select * from Movie where id=?", arrayOf(id), MovieRowMapper())
        if(!movies.any()) return null
        return _movieMapper.fromEntity(movies.first())
    }
}