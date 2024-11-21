package org.example.gateway.config;


import org.example.gateway.security.AuthotityConstant;
import org.example.gateway.security.jwt.JwtFilter;

import org.example.gateway.security.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final TokenProvider tokenProvider;

    public SecurityConfig( TokenProvider tokenProvider ) {
        this.tokenProvider = tokenProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http ) throws Exception {

        http
                .csrf( AbstractHttpConfigurer::disable );
        http
                .sessionManagement( s -> s.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) );
        http

                .authorizeHttpRequests( authz -> authz
                        //ENDPOINTS AUTENTICACION
                        .requestMatchers(HttpMethod.POST, "/api/authenticate").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/usuarios").permitAll()
                        //ENDPOINTS MONOPATIN
                        .requestMatchers( HttpMethod.POST,"/monopatines").hasAuthority( AuthotityConstant._ADMIN )
                        .requestMatchers(HttpMethod.GET, "/monopatines/**").hasAnyAuthority(AuthotityConstant._ENCARGADO, AuthotityConstant._ADMIN)
                        .requestMatchers(HttpMethod.DELETE, "/monopatines/{id}").hasAuthority(AuthotityConstant._ADMIN)
                        .requestMatchers(HttpMethod.GET, "/monopatines/cercanos/ubicacionX/{x}/ubicacionY/{y}").hasAnyAuthority(AuthotityConstant._USER, AuthotityConstant._ADMIN)
                        // VIAJES
                        .requestMatchers(HttpMethod.GET, "/viajes/{id}").hasAnyAuthority(AuthotityConstant._USER, AuthotityConstant._ADMIN)
                        .requestMatchers(HttpMethod.PUT, "/viajes/{id}").hasAnyAuthority(AuthotityConstant._USER, AuthotityConstant._ADMIN)
                        .requestMatchers(HttpMethod.PUT, "/viajes/{id}/ubicacionX/{x}/ubicacionY/{y}").hasAuthority(AuthotityConstant._USER)
                        .requestMatchers(HttpMethod.DELETE, "/viajes/{id}").hasAuthority(AuthotityConstant._ADMIN)
                        .requestMatchers(HttpMethod.GET, "/viajes/cantViajes/{cantViajes}/year/{anio}").hasAuthority(AuthotityConstant._ADMIN)
                        .requestMatchers(HttpMethod.POST, "/viajes").hasAnyAuthority(AuthotityConstant._USER, AuthotityConstant._ADMIN)
                        .requestMatchers(HttpMethod.GET, "/viajes").hasAuthority(AuthotityConstant._USER)
                        // GESTOR CUENTAS
                        .requestMatchers(HttpMethod.POST, "/GestorCuentas/**").hasAuthority(AuthotityConstant._USER)
                        .requestMatchers(HttpMethod.DELETE, "/GestorCuentas/usuarios/{id}").hasAuthority(AuthotityConstant._ADMIN)
                        .requestMatchers(HttpMethod.PUT, "/GestorCuentas/**").hasAuthority(AuthotityConstant._USER)
                        // MANTENIMIENTO
                        .requestMatchers(HttpMethod.PUT, "/mantenimiento").hasAuthority(AuthotityConstant._ENCARGADO)
                        // PARADAS
                        .requestMatchers(HttpMethod.GET, "/paradas/**").hasAuthority(AuthotityConstant._ADMIN)
                        .requestMatchers(HttpMethod.GET, "/paradas/ubicacionX/{x}/ubicacionY/{y}").hasAnyAuthority(AuthotityConstant._ADMIN, AuthotityConstant._USER)
                        // ADMIN
                        .requestMatchers(HttpMethod.GET, "/admin/**").hasAuthority(AuthotityConstant._ADMIN)
                        .anyRequest().authenticated()
                )
                .httpBasic( Customizer.withDefaults() )
                .addFilterBefore( new JwtFilter( this.tokenProvider ), UsernamePasswordAuthenticationFilter.class );
        return http.build();
    }
}