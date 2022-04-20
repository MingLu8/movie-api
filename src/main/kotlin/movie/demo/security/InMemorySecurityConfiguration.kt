//package movie.demo.security
//
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.core.userdetails.User
//import org.springframework.security.core.userdetails.UserDetails
//import org.springframework.security.core.userdetails.UserDetailsService
//import org.springframework.security.provisioning.InMemoryUserDetailsManager
//import org.springframework.security.web.SecurityFilterChain
//import org.springframework.web.cors.CorsConfiguration
//import org.springframework.web.cors.CorsConfigurationSource
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource
//import java.util.*
//
//
//@Configuration
//class InMemorySecurityConfiguration {
//    @Bean
//    fun filterChain(http: HttpSecurity): SecurityFilterChain {
//        http.authorizeRequests()
//            .antMatchers("/css/**").permitAll()
//            .antMatchers("/user/**").hasAuthority("ROLE_USER")
////            .antMatchers("/api/**").hasAuthority("ROLE_USER")
// //           .antMatchers("/swagger-ui/**").hasAuthority("ROLE_USER")
////            .and()
////            .formLogin()
//        http.cors().and().csrf().disable()
//        return http.build()
//    }
//
//    @Bean
//    fun userDetailsService(): UserDetailsService {
//        val user: UserDetails = User.withDefaultPasswordEncoder()
//            .username("user")
//            .password("password")
//            .roles("USER")
//            .build()
//        return InMemoryUserDetailsManager(user)
//    }
//
//    @Bean
//    fun corsConfigurationSource(): CorsConfigurationSource? {
//        val configuration = CorsConfiguration()
//        configuration.allowedOrigins = Arrays.asList("*")
//        configuration.allowedMethods = Arrays.asList("*")
//        configuration.allowedHeaders = Arrays.asList("*")
//        configuration.allowCredentials = true
//        val source = UrlBasedCorsConfigurationSource()
//        source.registerCorsConfiguration("/**", configuration)
//        return source
//    }
//}