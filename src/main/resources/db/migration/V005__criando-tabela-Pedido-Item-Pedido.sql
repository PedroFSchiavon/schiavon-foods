CREATE TABLE pedido(
    id BIGINT NOT NULL AUTO_INCREMENT,
    taxa_frete DECIMAL(3, 2) NOT NULL,
    valor_total DECIMAL(4, 2) NOT NULL,
    data_criacao DATETIME NOT NULL,
    data_confirmacao DATETIME,
    data_cancelamento DATETIME,
    data_entrega DATETIME,
    status_pedido VARCHAR(10),
    endereco_bairro      varchar(40),
    endereco_cep         varchar(8),
    endereco_complemento varchar(80),
    endereco_logradouro  varchar(100),
    endereco_numero      varchar(6),
    endereco_cidade_id   bigint,
    cliente_id BIGINT NOT NULL,
    restaurante_id BIGINT NOT NULL,
    forma_pagamento_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT pedido_cidade_id FOREIGN KEY (endereco_cidade_id) REFERENCES cidade (id),
    CONSTRAINT pedido_cliente_id FOREIGN KEY (cliente_id) REFERENCES usuario (id),
    CONSTRAINT pedido_restaurante_id FOREIGN KEY (restaurante_id) REFERENCES restaurante (id),
    CONSTRAINT pedido_forma_pagamento_id FOREIGN KEY (forma_pagamento_id) REFERENCES forma_pagamento (id)
)ENGINE = InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE item_pedido(
    id BIGINT NOT NULL AUTO_INCREMENT,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(3, 2) NOT NULL,
    preco_total DECIMAL(4, 2) NOT NULL,
    observacao VARCHAR(80),
    produto_id BIGINT NOT NULL,
    pedido_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT item_pedido_produto_id FOREIGN KEY (produto_id) REFERENCES produto (id),
    CONSTRAINT item_pedido_pedido_id FOREIGN KEY (pedido_id) REFERENCES pedido (id)
)ENGINE = InnoDB DEFAULT CHARSET=utf8;