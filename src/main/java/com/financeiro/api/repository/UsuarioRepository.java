package com.financeiro.api.repository;

import com.financeiro.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Método customizado para buscar usuário por e-mail
    Optional<Usuario> findByEmail(String email);
}
