package movie.demo.repository

import movie.demo.entity.Movie
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository


/**
 * Created by Abhishek Saxena on 14-03-2021.
 */

interface MovieRepository: CrudRepository<Movie, Int> {

    @Query("SELECT m FROM Movie as m")
    fun getAllMovies(): List<Movie>

}