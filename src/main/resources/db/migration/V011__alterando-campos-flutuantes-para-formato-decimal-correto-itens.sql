ALTER TABLE item_pedido DROP preco_total;
ALTER TABLE item_pedido DROP preco_unitario;

ALTER TABLE item_pedido ADD preco_total DECIMAL(6, 2) NOT NULL;
ALTER TABLE item_pedido ADD preco_unitario DECIMAL(6, 2) NOT NULL;