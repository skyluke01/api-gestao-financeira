CREATE TABLE transacoes (
                            id BIGSERIAL PRIMARY KEY,
                            descricao VARCHAR(255) NOT NULL,
                            valor NUMERIC(10, 2) NOT NULL,
                            tipo VARCHAR(50) NOT NULL,
                            data TIMESTAMP NOT NULL,
                            usuario_id BIGINT NOT NULL,
                            CONSTRAINT fk_transacao_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);