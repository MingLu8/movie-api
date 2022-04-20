package movie.demo.entity

import javax.persistence.*

@Entity
@Table(name = "movie")
data class Movie(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    var name: String,
    var rating : Double
)


@Entity
@Table(name = "userlogin")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    var username: String,
    var password : String
)