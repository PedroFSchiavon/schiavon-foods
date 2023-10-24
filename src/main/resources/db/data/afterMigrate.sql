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
delete from pedido;
delete from item_pedido;
delete from restaurante_usuario_responsaveis;

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
ALTER TABLE pedido AUTO_INCREMENT = 1;
ALTER TABLE item_pedido AUTO_INCREMENT = 1;
ALTER TABLE restaurante_usuario_responsaveis AUTO_INCREMENT = 1;

-- cozinha
insert into cozinha (nome) values ('Amazonense');
insert into cozinha (nome) values ('Mineira');
insert into cozinha (nome) values ('Italiana');
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
-- restaurante
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) values ('Cantinho do Burguer', 4.76, 2, utc_timestamp, utc_timestamp, true, false, '30730412', 'Rua pastelzinho fernandes', '27000', 'Apto 202 bloco 2', 'Pastel', 2);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento,endereco_bairro, endereco_cidade_id) values ('Vini burguer', 3.80, 2, utc_timestamp, utc_timestamp, true, false, '87296543', 'Rua Rocambole Ferreira', '2964', 'Apto 333 bloco 1', 'Beco do roca', 1);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento,endereco_bairro, endereco_cidade_id) values ('Paçai', 7.80, 1, utc_timestamp, utc_timestamp, true, false, '72172911', 'Rua pizza pezi', '123', 'Apto 777 bloco 4','pizza saboroza', 4);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento,endereco_bairro, endereco_cidade_id) values ('Forno da saudade', 1.15, 3, utc_timestamp, utc_timestamp, true, false, '26154987', 'Rua do Sushi', '7588', 'Apto 888 bloco 3', 'Liberdade', 3);
-- forma de pagamento
insert into forma_pagamento (descricao) values ('Cartão de crédito');
insert into forma_pagamento (descricao) values ('Cartão de débito');
insert into forma_pagamento (descricao) values ('Pix');
insert into forma_pagamento (descricao) values ('Dinheiro');
insert into forma_pagamento (descricao) values ('Boleto');
-- Restaurante e forma de pagamento
insert into restaurante_forma_pagamento values (1,1), (1,2), (1,3), (1,4), (2,1), (2,2), (2,3), (3,2), (3,4), (4,5);
-- Produtos
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Super Cantinho', 'O melhor podrão da cidade!', 33.33, true, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Cantinho xexelento', 'Até broxa quando aparece', 22.33, false, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Pizza italiana', 'Pizza da mais tradicional!', 45.50, true, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Especial da casa combo', 'O segundo melhor podrão da cidade', 50, true, 2);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Açai completo', 'Açai tradicional do sudeste.',15.99, true, 3);
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
-- Usuários responsaveis por Restaurantes
insert into restaurante_usuario_responsaveis (restaurante_id, usuario_id) values (4, 1), (3, 2), (2, 3), (1, 4);
-- Pedido
insert into pedido
    (codigo, taxa_frete, valor_total, data_criacao, data_confirmacao, status_pedido, endereco_bairro,
         endereco_cep, endereco_logradouro, endereco_numero, endereco_cidade_id, cliente_id, restaurante_id, forma_pagamento_id)
            values ('6918294c-6d66-11ee-ba51-58a02333c4b7', 5.50, 95.49, '2023-09-01T18:39:02', utc_timestamp, 'CONFIRMADO', 'Bairro A', '30765987', 'Rua Boa esperanca',
                    '1234', 1, 1, 1, 2);
insert into pedido
    (codigo, taxa_frete, valor_total, data_criacao, data_confirmacao, status_pedido, endereco_bairro,
        endereco_cep, endereco_logradouro, endereco_numero, endereco_cidade_id, cliente_id, restaurante_id, forma_pagamento_id)
            values ('286e862c-b3f9-46ee-8e94-986d82fca150', 3.80, 149.30, '2023-10-01T14:39:02', utc_timestamp, 'CONFIRMADO', 'Bairro B', '10665987', 'Rua da Vitória',
                '4321', 2, 2, 2, 1);
insert into pedido
    (codigo, taxa_frete, valor_total, data_criacao, data_confirmacao, status_pedido, endereco_bairro,
        endereco_cep, endereco_logradouro, endereco_numero, endereco_cidade_id, cliente_id, restaurante_id, forma_pagamento_id)
            values ('d4168b2c-1085-4dff-9d6a-af076ea0fd6c', 7.80, 52.30, '2023-09-15T03:39:02', utc_timestamp, 'CONFIRMADO', 'Bairro c', '90765123', 'Rua da Perdição',
                '4356', 3, 3, 3, 4);
insert into pedido
    (codigo, taxa_frete, valor_total, data_criacao, data_confirmacao, status_pedido, endereco_bairro,
        endereco_cep, endereco_logradouro, endereco_numero, endereco_cidade_id, cliente_id, restaurante_id, forma_pagamento_id)
            values ('44507a4b-7aeb-4b49-b9a4-a44d064559ef', 7.50, 915.49, '2023-10-24T23:39:02', utc_timestamp, 'CONFIRMADO', 'Bairro A', '30765987', 'Rua Boa esperanca',
                '1234', 1, 1, 2, 4);
-- Item Pedido
insert into item_pedido (quantidade, observacao, produto_id, pedido_id, preco_total, preco_unitario)
    values (2, 'Sem abacaxi', 1, 1, 66.66, 33.33);
insert into item_pedido (quantidade, observacao, produto_id, pedido_id, preco_total, preco_unitario)
    values (1, 'Capricha no molho', 2, 1, 23.33, 23.33);
insert into item_pedido (quantidade, observacao, produto_id, pedido_id, preco_total, preco_unitario)
    values (1, 'Bota muito recheio', 3, 2, 45.50, 45.50);
insert into item_pedido (quantidade, observacao, produto_id, pedido_id, preco_total, preco_unitario)
    values (2, 'Vocês são perfeitos', 4, 2, 100, 50);
insert into item_pedido (quantidade, observacao, produto_id, pedido_id, preco_total, preco_unitario)
    values (1, 'Leite em pó extra', 5, 3, 32.99, 16.99);
insert into item_pedido (quantidade, observacao, produto_id, pedido_id, preco_total, preco_unitario)
values (2, 'Bota poucp recheio', 3, 4, 90, 45.50);

