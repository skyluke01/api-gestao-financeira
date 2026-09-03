package com.financeiro.api.service;

import com.financeiro.api.model.Transacao;
import com.financeiro.api.model.Usuario;
import com.financeiro.api.repository.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final UsuarioService usuarioService;

    public TransacaoService(TransacaoRepository transacaoRepository, UsuarioService usuarioService) {
        this.transacaoRepository = transacaoRepository;
        this.usuarioService = usuarioService;
    }

    public Transacao cadastrarTransacao(Transacao transacao, Long usuarioId) {
        // Busca o usuário pelo ID para garantir que ele existe
        Usuario usuario = usuarioService.buscarPorId(usuarioId);

        // Associa a transação ao usuário encontrado
        transacao.setUsuario(usuario);

        return transacaoRepository.save(transacao);
    }

    public List<Transacao> listarPorUsuario(Long usuarioId, String tipo) {
        if (tipo != null && !tipo.isBlank()) {
            return transacaoRepository.findByUsuarioIdAndTipo(usuarioId, tipo.toUpperCase());
        }
        return transacaoRepository.findByUsuarioId(usuarioId);
    }

    public void deletarTransacao(Long Id) {
        if (!transacaoRepository.existsById(Id)) {
            throw new RuntimeException("Transação não encontrada com o ID: " + Id);
        }
        transacaoRepository.deleteById(Id);
    }
}
