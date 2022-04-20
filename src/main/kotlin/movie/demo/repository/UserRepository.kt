package movie.demo.repository

import movie.demo.entity.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Int> {
    fun findByUsername(email: String): User?

}
