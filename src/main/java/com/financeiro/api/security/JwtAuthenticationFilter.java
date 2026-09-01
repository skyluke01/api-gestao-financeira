package com.financeiro.api.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // Se não tem cabeçalho Authorization ou não começa com Bearer, passa adiante sem autenticar
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extrai o token removendo a palavra "Bearer "
        jwt = authHeader.substring(7);
        userEmail = jwtService.extrairUsername(jwt);

        // Se encontrou o e-mail e o usuário ainda não está autenticado no contexto do Spring
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Valida o token
            if (jwtService.validarToken(jwt, userEmail)) {
                // Cria o objeto de autenticação do Spring Security
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userEmail, null, new ArrayList<>()
                );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Define o usuário como autenticado no contexto atual da requisição
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Continua o fluxo da requisição
        filterChain.doFilter(request, response);
    }
}
