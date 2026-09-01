package com.financeiro.api.controller;

import com.financeiro.api.dto.LoginDto;
import com.financeiro.api.model.Usuario;
import com.financeiro.api.security.JwtService;
import com.financeiro.api.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final JwtService jwtService;

    public AuthController(UsuarioService usuarioService, JwtService jwtService) {
        this.usuarioService = usuarioService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDto loginDto) {
        // Autentica o usuário
        Usuario usuario = usuarioService.autenticar(loginDto.getEmail(), loginDto.getSenha());

        // Gera o token JWT usando o e-mail do usuário
        String token = jwtService.gerarToken(usuario.getEmail());

        // Retorna o token dentro de um JSON
        Map<String, String> resposta = new HashMap<>();
        resposta.put("token", token);

        return ResponseEntity.ok(resposta);
    }
}
