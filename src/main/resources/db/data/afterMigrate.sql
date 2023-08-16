set foreign_key_checks = 0;

delete from cidade;
delete from cozinha;
delete from estado;
delete from forma_pagamento;
delete from grupo;
delete from grupo_permissao;
delete from grupo_usuario;
delete from permissao;
delete from produto;
delete from restaurante;
delete from restaurante_forma_pagamento;
delete from usuario;

set foreign_key_checks = 1;

ALTER TABLE cidade AUTO_INCREMENT = 1;
ALTER TABLE cozinha AUTO_INCREMENT = 1;
ALTER TABLE estado AUTO_INCREMENT = 1;
ALTER TABLE forma_pagamento AUTO_INCREMENT = 1;
ALTER TABLE grupo AUTO_INCREMENT = 1;
ALTER TABLE permissao AUTO_INCREMENT = 1;
ALTER TABLE produto AUTO_INCREMENT = 1;
ALTER TABLE restaurante AUTO_INCREMENT = 1;
ALTER TABLE usuario AUTO_INCREMENT = 1;

-- cozinha
insert into cozinha (nome) values ('Amazonense');
insert into cozinha (nome) values ('Mineira');
insert into cozinha (nome) values ('Italiana');
-- restaurante
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Cantinho do Burguer', 4.76, 2, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Vini burguer', 3.80, 2, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Paçai', 7.80, 1, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Forno da saudade', 1.15, 3, utc_timestamp, utc_timestamp);
-- estado
insert into estado (nome) values ('Minas Gerais');
insert into estado (nome) values ('São Paulo');
insert into estado (nome) values ('Rio Grande do Sul');
insert into estado (nome) values ('Santa Catarina');
-- cidade
insert into cidade (nome, estado_id) values ('Campinas', 2);
insert into cidade (nome, estado_id) values ('Porto Alegre', 3);
insert into cidade (nome, estado_id) values ('Florianopolis', 4);
insert into cidade (nome, estado_id) values ('Belo Horizonte', 1);
-- forma de pagamento
insert into forma_pagamento (descricao) values ('Cartão de crédito');
insert into forma_pagamento (descricao) values ('Cartão de débito');
insert into forma_pagamento (descricao) values ('Pix');
insert into forma_pagamento (descricao) values ('Dinheiro');
insert into forma_pagamento (descricao) values ('Boleto');
-- Restaurante e forma de pagamento
insert into restaurante_forma_pagamento values (1,1), (1,2), (1,3), (1,4), (2,1), (2,2), (2,3), (3,2), (3,4), (4,5);
-- Produtos
insert into produto (nome, preco, ativo, restaurante_id) values ('Super Cantinho', 33.33, true, 1);
insert into produto (nome, preco, ativo, restaurante_id) values ('Pizza italiana', 45.50, true, 4);
insert into produto (nome, preco, ativo, restaurante_id) values ('Especial da casa combo', 50, true, 2);
insert into produto (nome, preco, ativo, restaurante_id) values ('Açai completo', 15.99, true, 3);
-- Permissao
insert into permissao (nome, descricao) values ('Vender', 'Permissao para vender');
insert into permissao (nome, descricao) values ('Criar produto', 'Permissao para criar');
insert into permissao (nome, descricao) values ('Deletar produto', 'Permissao para deletar');
insert into permissao (nome, descricao) values ('Comprar', 'Permissao para comprar');
insert into permissao (nome, descricao) values ('Cadastrar forma de pagamento', 'Permissao para cadastrar forma de pagamento');
insert into permissao (nome, descricao) values ('Visualizar restaurante', 'Permissao para visualizar restaurante');
-- Grupo
insert into grupo (nome) values ('Vendedor');
insert into grupo (nome) values ('Admin');
insert into grupo (nome) values ('Consumidor');
insert into grupo (nome) values ('Visitante');
-- Grupo de Permissao
insert  into grupo_permissao values (1,1), (1,2), (1,4), (1,5), (1,6);
insert  into grupo_permissao values (2,1), (2,2), (2,3), (2,4), (2,5), (2,6);
insert  into grupo_permissao values (3,4), (3,5), (3,6);
insert  into grupo_permissao values (4,6);
-- Usuario
insert into usuario (nome, email, senha) values ('Pedro', 'pedro@gmail.com', 'senhasenha');
insert into usuario (nome, email, senha) values ('Andressa', 'andressa@gmail.com', 'senhasenha123');
insert into usuario (nome, email, senha) values ('Noah', 'noah@gmail.com', 'broabroa');
insert into usuario (nome, email, senha) values ('Amora', 'amora@gmail.com', 'rozilda');
-- Grupo de usuario
insert into grupo_usuario values (1,2), (2,1), (3,4), (4,3);