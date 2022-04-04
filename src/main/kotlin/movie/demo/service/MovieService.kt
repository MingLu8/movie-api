package movie.demo.service

import movie.demo.dto.MovieDTO

interface MovieService {
    fun getMovies() : List<MovieDTO>
    fun createMovie(movieDTO: MovieDTO): Int
}