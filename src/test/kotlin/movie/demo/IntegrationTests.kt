package movie.demo

import com.ninjasquad.springmockk.MockkBean
import movie.demo.repository.MovieRepository
import movie.demo.service.MovieService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests {
    @MockkBean
    lateinit var movieService: MovieService

    @MockkBean
    lateinit var movieRepository: MovieRepository

    @Autowired
    private lateinit var restTemplate: TestRestTemplate
    @BeforeAll
    fun setup() {
        println(">> Setup")
    }

    @Test
    fun `Assert blog page title, content and status code`() {
        println(">> Assert blog page title, content and status code")
        val entity = restTemplate.getForEntity<String>("/")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains("<h1>Blog</h1>", "Reactor")
    }

    @AfterAll
    fun teardown() {
        println(">> Tear down")
    }

}