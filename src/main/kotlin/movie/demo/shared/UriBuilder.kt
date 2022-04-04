package movie.demo.shared

import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

fun String.toLocation() : URI =
    ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(this)
        .toUri()

fun Int.toLocation() : URI =
    ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(this)
        .toUri()
