ALTER TABLE pedido DROP taxa_frete;
ALTER TABLE pedido DROP valor_total;

ALTER TABLE pedido ADD taxa_frete DECIMAL(6, 2) NOT NULL;
ALTER TABLE pedido ADD valor_total DECIMAL(6, 2) NOT NULL;