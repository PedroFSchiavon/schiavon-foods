CREATE TABLE restaurante_usuario_responsaveis(
    id BIGINT AUTO_INCREMENT,
    restaurante_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    PRIMARY KEY (id)
) engine = InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE restaurante_usuario_responsaveis
    ADD CONSTRAINT responsaveis_restaurante_id
        FOREIGN KEY (restaurante_id) REFERENCES restaurante (id);

ALTER TABLE restaurante_usuario_responsaveis
    ADD CONSTRAINT responsaveis_usuario_id
        FOREIGN KEY (usuario_id) REFERENCES usuario (id);