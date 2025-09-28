package com.example.demo.Configuration;


import com.example.demo.Middleware.AuthorizationFilter;
import com.example.demo.Services.UserProfileService;
import com.example.demo.Utilities.CookieUtils;
import com.example.demo.Utilities.JwtUtils;
import com.example.demo.Utilities.MiddleWareUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import com.example.demo.Entities.UsersProfile.Role;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final MiddleWareUtils middleWareUtils;
    private final JwtUtils jwtUtils;
    private final CookieUtils cookieUtils;
    private final UserProfileService userProfileService;

    public SecurityConfig(MiddleWareUtils middleWareUtils, JwtUtils jwtUtils, CookieUtils cookieUtils, UserProfileService userProfileService) {
        this.middleWareUtils = middleWareUtils;
        this.jwtUtils = jwtUtils;
        this.cookieUtils = cookieUtils;
        this.userProfileService = userProfileService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults()) // CORS is handled by your CorsConfig class
            .csrf(csrf -> csrf.disable())

            .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            .authorizeHttpRequests(auth -> auth
                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .requestMatchers(HttpMethod.DELETE, "/userProfile").hasAnyAuthority(Role.USER.toString())
                    .requestMatchers("/userYearActivity/**").hasAnyAuthority(Role.USER.toString())
                    .requestMatchers("/userProfile/**").permitAll()
            )

                .addFilterBefore(new AuthorizationFilter(middleWareUtils, jwtUtils, cookieUtils, userProfileService), UsernamePasswordAuthenticationFilter.class)

            .exceptionHandling(exceptions -> exceptions
                    .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
            );

        return http.build();
    }
}