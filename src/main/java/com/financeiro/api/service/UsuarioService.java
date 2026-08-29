package com.financeiro.api.service;

import com.financeiro.api.model.Usuario;
import org.springframework.stereotype.Service;
import com.financeiro.api.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    // Injeção de dependência via construtor
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvarUsuario(Usuario usuario) {
        // Futuramente criptografar a senha antes de salvar
        return usuarioRepository.save(usuario);
    }
}
