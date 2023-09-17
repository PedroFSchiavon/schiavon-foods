ALTER TABLE produto add descricao VARCHAR(80) NOT NULL;
UPDATE produto set produto.descricao = 'produto';