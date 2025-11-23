package br.com.msp.busiq.config.security;

import br.com.msp.busiq.infrastructure.gateway.auth.apikey.ApiKeyAuthorizationManager;
import br.com.msp.busiq.infrastructure.gateway.auth.jwt.JwtAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final ApiKeyAuthorizationManager apiKeyAuthorizationManager;

    public SecurityConfig(JwtAuthenticationProvider jwtAuthenticationProvider,
                          ApiKeyAuthorizationManager apiKeyAuthorizationManager) {
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
        this.apiKeyAuthorizationManager = apiKeyAuthorizationManager;
    }

    private final String[] GTFS_ENDPOINTS = {
            "/agency/**",
            "/calendar/**",
            "/fare-attributes/**",
            "/fare-rules/**",
            "/frequencies/**",
            "/routes/**",
            "/stops/**",
            "/stop-times/**",
            "/trips/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager)
            throws Exception {
        JwtFilter jwtFilter = new JwtFilter(authenticationManager);

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, GTFS_ENDPOINTS).access(apiKeyAuthorizationManager)
                        .requestMatchers(HttpMethod.POST, "/api-key").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/users").authenticated()
                        .requestMatchers(HttpMethod.POST, "/users", "/auth").permitAll()
                        .anyRequest().denyAll())
                .authenticationProvider(jwtAuthenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
