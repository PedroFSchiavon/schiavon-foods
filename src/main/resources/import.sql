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