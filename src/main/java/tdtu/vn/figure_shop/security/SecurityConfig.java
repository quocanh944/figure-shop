package tdtu.vn.figure_shop.security;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.models.OpenAPI;
import jakarta.servlet.FilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@OpenAPIDefinition(info = @Info(title = "REST API", version = "1.0",
        description = "REST API description...",
        contact = @Contact(name = "Name Surname")),
        security = {@SecurityRequirement(name = "bearerToken")}
)
@SecuritySchemes({
        @SecurityScheme(name = "bearerToken", type = SecuritySchemeType.HTTP,
                scheme = "bearer", bearerFormat = "JWT")
})
public class SecurityConfig {

    private JwtAuthEntryPoint jwtAuthEntryPoint;
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws  Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWTFilter jwtFilter() {
        return new JWTFilter();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {

        return (web) -> web
                .ignoring()
                .requestMatchers(
                        "/swagger-ui/**", "/v3/api-docs/**"
                );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(ex -> {
                    ex.authenticationEntryPoint(jwtAuthEntryPoint);
                })
                .sessionManagement(session -> {
                  session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .authorizeHttpRequests(auth -> {
                    auth
                            .requestMatchers("/api/auth/**")
                            .permitAll()
                            .anyRequest()
                            .authenticated();
                });

        httpSecurity.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
