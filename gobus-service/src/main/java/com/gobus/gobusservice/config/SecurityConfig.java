package com.gobus.gobusservice.config;

import com.gobus.gobusservice.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
@AllArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, GobusPasswordEncoder gobusPasswordEncoder)
            throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(gobusPasswordEncoder);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfigurationSource corsConfigurationSource) throws Exception {
        return http.cors(cors -> cors.configurationSource(corsConfigurationSource))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> {
                    requests.anyRequest().permitAll();
                })
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // Allow credentials such as cookies
        config.addAllowedOrigin("http://localhost:3000"); // Allow a specific origin
        config.addAllowedHeader("*"); // Allow all headers
        config.addAllowedMethod("*"); // Allow all methods (GET, POST, PUT, DELETE, etc.)
        config.addAllowedMethod(HttpMethod.DELETE);
        config.addAllowedMethod(HttpMethod.POST);
        config.addAllowedMethod(HttpMethod.PUT);
        config.addAllowedMethod(HttpMethod.GET);
        source.registerCorsConfiguration("/**", config);
        return source;
    }

}
