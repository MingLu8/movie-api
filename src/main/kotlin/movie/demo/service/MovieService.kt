package movie.demo.service

import movie.demo.dto.MovieDTO
import movie.demo.web.rest.CreateMovieCommand
import movie.demo.web.rest.UpdateMovieCommand

interface MovieService {
    fun getMovies() : List<MovieDTO>
    fun createMovie(createMovieCommand: CreateMovieCommand): MovieDTO
    fun getMovieById(id:Int): MovieDTO?
    fun updateMovie(id: Int, cmd: UpdateMovieCommand): MovieDTO?
    fun deleteMovie(id: Int): Unit
}