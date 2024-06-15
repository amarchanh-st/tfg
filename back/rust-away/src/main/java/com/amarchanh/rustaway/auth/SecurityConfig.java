package com.amarchanh.rustaway.auth;

import com.amarchanh.rustaway.auth.filter.JwtAuthenticationFilter;
import com.amarchanh.rustaway.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService.userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorization -> authorization
                        .requestMatchers(HttpMethod.POST, "/user/log-in", "/user/sign-up").permitAll()
                        .requestMatchers(HttpMethod.GET, "/budget/**").hasAnyRole("CLIENT", "WORKER")
                        .requestMatchers(HttpMethod.POST, "/budget/**").hasAnyRole("CLIENT", "WORKER")
                        .requestMatchers(HttpMethod.GET, "/user/**").hasAnyRole("CLIENT", "WORKER")
                        .requestMatchers(HttpMethod.PUT, "/user/**").hasAnyRole("CLIENT", "WORKER")
                        .requestMatchers(HttpMethod.PUT, "/status/**").hasAnyRole( "WORKER")
                        .requestMatchers(HttpMethod.PUT, "/chats/**").hasAnyRole( "CLIENT", "WORKER")
                        .requestMatchers(HttpMethod.PUT, "/images/**").hasAnyRole( "CLIENT", "WORKER")
                        .requestMatchers(HttpMethod.PUT, "/budget/**").hasAnyRole( "WORKER")
                        .requestMatchers("/v3/**", "/swagger-ui/**", "/swagger-ui.html", "/webjars/**",
                                "/swagger-resources/**", "/v2/api-docs", "openapi.yaml").permitAll()
                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

       return http.build();
    }
}
