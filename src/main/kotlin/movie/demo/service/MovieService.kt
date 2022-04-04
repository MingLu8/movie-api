package movie.demo.service

import movie.demo.dto.MovieDTO
import movie.demo.web.rest.CreateMovieCommand

interface MovieService {
    fun getMovies() : List<MovieDTO>
    fun createMovie(createMovieCommand: CreateMovieCommand): MovieDTO
    fun getMovieById(id:Int): MovieDTO?
}