package com.financeiro.api.controller;

import com.financeiro.api.model.Transacao;
import com.financeiro.api.service.TransacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping("/{usuarioId}")
    public ResponseEntity<Transacao> cadastrarTransacao(@PathVariable Long usuarioId, @RequestBody Transacao transacao) {
        Transacao novaTransacao = transacaoService.cadastrarTransacao(transacao, usuarioId);
        return ResponseEntity.ok(novaTransacao);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Transacao>> listarPorUsuario(
            @PathVariable Long usuarioId,
            @RequestParam(required = false) String tipo) {
        List<Transacao> transacoes = transacaoService.listarPorUsuario(usuarioId, tipo);
        return ResponseEntity.ok(transacoes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTransacao(@PathVariable Long id) {
        transacaoService.deletarTransacao(id);
        return ResponseEntity.noContent().build();
    }
}