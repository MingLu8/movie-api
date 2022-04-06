package movie.demo

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import movie.demo.dto.MovieDTO
import movie.demo.repository.MovieRepository
import movie.demo.service.MovieService
import movie.demo.web.rest.MovieResource
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.security.Principal

@ExtendWith(SpringExtension::class)
@WebMvcTest(MovieResource::class)
@AutoConfigureMockMvc(addFilters = false)
internal class MovieControllerTest {
    @MockkBean
    lateinit var movieService: MovieService

    @MockkBean
    lateinit var movieRepository: MovieRepository

    @Autowired
    private lateinit var mockMvc: MockMvc

    private val moviename = "movie@example.com"

    private val mockPrincipal = mockk<Principal> {
        every { name } returns moviename
    }

    @Test
    fun `when the movie exists, the movie info is returned`() {
        val expectedMovie = MovieDTO(3, "movie@example.com", 5.0)
        val objectMapper = ObjectMapper()
        val movieInfoJSON = objectMapper.writeValueAsString(expectedMovie)
        every { movieService.getMovieById(3) } returns expectedMovie

        performMe()
            .andExpect(status().isOk)
            .andExpect(content().json(movieInfoJSON))
            .andReturn()

        verify { movieService.getMovieById(3) }
    }

    @Test
    fun `when the movie does not exist, there is a not found error`() {
        every { movieService.getMovieById(3) } returns null

        performMe()
            .andExpect(status().isNotFound)
            .andReturn()

        every { movieService.getMovieById(3) }
    }

    private fun performMe() =
        mockMvc.perform(
            MockMvcRequestBuilders.get("/movie/me")
            .principal(mockPrincipal)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
}