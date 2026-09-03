package com.financeiro.api.dto;

import com.financeiro.api.model.Usuario;
import java.time.LocalDateTime;
public class UsuarioResponseDto {

    private Long id;
    private String nome;
    private String email;
    private LocalDateTime criadoEm;

    public UsuarioResponseDto(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.criadoEm = usuario.getCriadoEm();
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }
}
