package movie.demo.security

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author sourabh
 * @implNote Configuration class for Open API Specifications
 */
@Configuration
class OpenApiConfig {
    /**
     * Open API Configuration Bean
     *
     * @param title
     * @param version
     * @param description
     * @return
     */
    @Bean
    fun openApiConfiguration(
        @Value("\${openapi.title}") title: String?,
        @Value("\${openapi.version}") version: String?,
        @Value("\${openapi.description}") description: String?
    ): OpenAPI {
        return OpenAPI()
            .addSecurityItem(SecurityRequirement().addList(SECURITY_SCHEME_NAME))
            .components(
                Components()
                    .addSecuritySchemes(
                        SECURITY_SCHEME_NAME,
                        SecurityScheme()
                            .name(SECURITY_SCHEME_NAME)
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT")
                    )
            )
            .info(
                Info()
                    .title(title)
                    .version(version)
                    .description(description)
                    .termsOfService("Terms of service")
                    .license(license)
                    .contact(contact)
            )
    }

    /**
     * Contact details for the developer(s)
     *
     * @return
     */
    private val contact: Contact
        get() {
            val contact = Contact()
            contact.email = "sourabhanant@gmail.com"
            contact.name = "Sourabh Parsekar"
            contact.url = "https://medium.com/@sourabhanant"
            contact.extensions = emptyMap()
            return contact
        }

    /**
     * License creation
     *
     * @return
     */
    private val license: License
        get() {
            val license = License()
            license.name = "Apache License, Version 2.0"
            license.url = "http://www.apache.org/licenses/LICENSE-2.0"
            license.extensions = emptyMap()
            return license
        }

    companion object {
        const val SECURITY_SCHEME_NAME = "Bearer oAuth Token"
    }
}