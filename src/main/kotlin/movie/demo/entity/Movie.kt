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