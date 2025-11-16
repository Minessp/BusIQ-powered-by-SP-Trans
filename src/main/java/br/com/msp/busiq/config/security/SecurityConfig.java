package br.com.msp.busiq.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, authenticatedEndpoints()).authenticated()
                        .anyRequest().permitAll());

        return http.build();
    }

    private String[] authenticatedEndpoints() {
        return new String[] {
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
    }
}
