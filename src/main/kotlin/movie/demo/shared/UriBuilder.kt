package movie.demo.shared

import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

fun Any.toLocation() : URI =
    ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(this)
        .toUri()