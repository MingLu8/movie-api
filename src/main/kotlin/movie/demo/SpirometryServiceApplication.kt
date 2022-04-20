package movie.demo

import movie.demo.shared.ExceptionHandler.Companion.logger
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.net.InetAddress
import java.net.UnknownHostException

@SpringBootApplication
class SpirometryServiceApplication{
	@Bean
	fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
		return BCryptPasswordEncoder()
	}
}

fun main(args: Array<String>) {
	val env = runApplication<SpirometryServiceApplication>(*args).environment

	var protocol = "http"
	if (env.getProperty("server.ssl.key-store") != null) {
		protocol = "https"
	}
	val serverPort = env.getProperty("server.port")
	var contextPath = env.getProperty("server.servlet.context-path")
	if (contextPath.isNullOrBlank()) {
		contextPath = ""
	}
	var hostAddress = "localhost"
	try {
		hostAddress = InetAddress.getLocalHost().hostAddress
	} catch (e: UnknownHostException) {
		logger.warn("The host name could not be determined, using `localhost` as fallback")
	}
	logger.info(
		"\n----------------------------------------------------------\n\t" +
				"Application '{}' is running! Access URLs:\n\t" +
				"Local: \t\t{}://localhost:{}{}/swagger-ui/index.html\n\t" +
				"External: \t{}://{}:{}{}/swagger-ui/index.html\n\t" +
				"Profile(s): \t{}\n----------------------------------------------------------",
		"spirometer service",
		protocol,
		serverPort,
		contextPath,
		protocol,
		hostAddress,
		serverPort,
		contextPath,
		env.activeProfiles
	)
}

