ALTER TABLE pedido ADD codigo VARCHAR(36) NOT NULL;
UPDATE pedido set codigo = uuid();

ALTER TABLE pedido ADD CONSTRAINT uni_pedido_codigo UNIQUE (codigo);