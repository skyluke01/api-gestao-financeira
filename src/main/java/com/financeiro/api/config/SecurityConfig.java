package com.financeiro.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desativar CSRF para testes com APIs REST
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/usuarios/cadastrar").permitAll() // Liberar a rota de cadastro
                        .anyRequest().authenticated() // Manter as outras rotas exigindo autenticação
                );

        return http.build();
    }
}
