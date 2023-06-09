
    create table cidade (
       id bigint not null auto_increment,
        nome varchar(255) not null,
        estado_id bigint not null,
        primary key (id)
    ) engine=InnoDB;

    create table cozinha (
       id bigint not null auto_increment,
        nome varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table estado (
       id bigint not null auto_increment,
        nome varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table forma_pagamento (
       id bigint not null auto_increment,
        descricao varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table grupo (
       id bigint not null auto_increment,
        nome varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table grupo_permissao (
       grupo_id bigint not null,
        permissao_id bigint not null
    ) engine=InnoDB;

    create table grupo_usuario (
       usuario_id bigint not null,
        grupo_id bigint not null
    ) engine=InnoDB;

    create table permissao (
       id bigint not null auto_increment,
        descricao varchar(255) not null,
        nome varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table produto (
       id bigint not null auto_increment,
        ativo bit not null,
        nome varchar(255),
        preco decimal(19,2),
        restaurante_id bigint not null,
        primary key (id)
    ) engine=InnoDB;

    create table restaurante (
       id bigint not null auto_increment,
        data_atualizacao datetime not null,
        data_cadastro datetime not null,
        endereco_bairro varchar(255),
        endereco_cep varchar(255),
        endereco_complemento varchar(255),
        endereco_logradouro varchar(255),
        endereco_numero varchar(255),
        nome varchar(255),
        taxa_frete decimal(19,2),
        cozinha_id bigint not null,
        endereco_cidade_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table restaurante_forma_pagamento (
       restaurante_id bigint not null,
        forma_pagamento_id bigint not null
    ) engine=InnoDB;

    create table usuario (
       id bigint not null auto_increment,
        data_cadastro datetime,
        email varchar(255) not null,
        nome varchar(255) not null,
        senha varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    alter table cidade 
       add constraint FKkworrwk40xj58kevvh3evi500 
       foreign key (estado_id) 
       references estado (id);

    alter table grupo_permissao 
       add constraint FKh21kiw0y0hxg6birmdf2ef6vy 
       foreign key (permissao_id) 
       references permissao (id);

    alter table grupo_permissao 
       add constraint FKta4si8vh3f4jo3bsslvkscc2m 
       foreign key (grupo_id) 
       references grupo (id);

    alter table grupo_usuario 
       add constraint FKsefl4bg3whol4iks53t2mgc9l 
       foreign key (grupo_id) 
       references grupo (id);

    alter table grupo_usuario 
       add constraint FKnoonyfhrgll54jvve2bhld4va 
       foreign key (usuario_id) 
       references usuario (id);

    alter table produto 
       add constraint FKb9jhjyghjcn25guim7q4pt8qx 
       foreign key (restaurante_id) 
       references restaurante (id);

    alter table restaurante 
       add constraint FK76grk4roudh659skcgbnanthi 
       foreign key (cozinha_id) 
       references cozinha (id);

    alter table restaurante 
       add constraint FKbc0tm7hnvc96d8e7e2ulb05yw 
       foreign key (endereco_cidade_id) 
       references cidade (id);

    alter table restaurante_forma_pagamento 
       add constraint FK7aln770m80358y4olr03hyhh2 
       foreign key (forma_pagamento_id) 
       references forma_pagamento (id);

    alter table restaurante_forma_pagamento 
       add constraint FKa30vowfejemkw7whjvr8pryvj 
       foreign key (restaurante_id) 
       references restaurante (id);
insert into cozinha (nome) values ('Amazonense');
insert into cozinha (nome) values ('Mineira');
insert into cozinha (nome) values ('Italiana');
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Cantinho do Burguer', 4.76, 2, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Vini burguer', 3.80, 2, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Paçai', 7.80, 1, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Forno da saudade', 1.15, 3, utc_timestamp, utc_timestamp);
insert into estado (nome) values ('Minas Gerais');
insert into estado (nome) values ('São Paulo');
insert into estado (nome) values ('Rio Grande do Sul');
insert into estado (nome) values ('Santa Catarina');
insert into cidade (nome, estado_id) values ('Campinas', 2);
insert into cidade (nome, estado_id) values ('Porto Alegre', 3);
insert into cidade (nome, estado_id) values ('Florianopolis', 4);
insert into cidade (nome, estado_id) values ('Belo Horizonte', 1);
insert into forma_pagamento (descricao) values ('Cartão de crédito');
insert into forma_pagamento (descricao) values ('Cartão de débito');
insert into forma_pagamento (descricao) values ('Pix');
insert into forma_pagamento (descricao) values ('Dinheiro');
insert into forma_pagamento (descricao) values ('Boleto');
insert into restaurante_forma_pagamento values (1,1), (1,2), (1,3), (1,4), (2,1), (2,2), (2,3), (3,2), (3,4), (4,5);
insert into produto (nome, preco, ativo, restaurante_id) values ("Super Cantinho", 33.33, true, 1);
insert into produto (nome, preco, ativo, restaurante_id) values ("Pizza italiana", 45.50, true, 4);
insert into produto (nome, preco, ativo, restaurante_id) values ("Especial da casa combo", 50, true, 2);
insert into produto (nome, preco, ativo, restaurante_id) values ("Açai completo", 15.99, true, 3);
insert into permissao (nome, descricao) values ("Vender", "Permissao para vender");
insert into permissao (nome, descricao) values ("Criar produto", "Permissao para criar");
insert into permissao (nome, descricao) values ("Deletar produto", "Permissao para deletar");
insert into permissao (nome, descricao) values ("Comprar", "Permissao para comprar");
insert into permissao (nome, descricao) values ("Cadastrar forma de pagamento", "Permissao para cadastrar forma de pagamento");
insert into permissao (nome, descricao) values ("Visualizar restaurante", "Permissao para visualizar restaurante");
insert into grupo (nome) values ("Vendedor");
insert into grupo (nome) values ("Admin");
insert into grupo (nome) values ("Consumidor");
insert into grupo (nome) values ("Visitante");
insert  into grupo_permissao values (1,1), (1,2), (1,4), (1,5), (1,6);
insert  into grupo_permissao values (2,1), (2,2), (2,3), (2,4), (2,5), (2,6);
insert  into grupo_permissao values (3,4), (3,5), (3,6);
insert  into grupo_permissao values (4,6);
insert into usuario (nome, email, senha) values ("Pedro", "pedro@gmail.com", "senhasenha");
insert into usuario (nome, email, senha) values ("Andressa", "andressa@gmail.com", "senhasenha123");
insert into usuario (nome, email, senha) values ("Noah", "noah@gmail.com", "broabroa");
insert into usuario (nome, email, senha) values ("Amora", "amora@gmail.com", "rozilda");
insert into grupo_usuario values (1,2), (2,1), (3,4), (4,3);

    create table cidade (
       id bigint not null auto_increment,
        nome varchar(255) not null,
        estado_id bigint not null,
        primary key (id)
    ) engine=InnoDB;

    create table cozinha (
       id bigint not null auto_increment,
        nome varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table estado (
       id bigint not null auto_increment,
        nome varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table forma_pagamento (
       id bigint not null auto_increment,
        descricao varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table grupo (
       id bigint not null auto_increment,
        nome varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table grupo_permissao (
       grupo_id bigint not null,
        permissao_id bigint not null
    ) engine=InnoDB;

    create table grupo_usuario (
       usuario_id bigint not null,
        grupo_id bigint not null
    ) engine=InnoDB;

    create table permissao (
       id bigint not null auto_increment,
        descricao varchar(255) not null,
        nome varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table produto (
       id bigint not null auto_increment,
        ativo bit not null,
        nome varchar(255),
        preco decimal(19,2),
        restaurante_id bigint not null,
        primary key (id)
    ) engine=InnoDB;

    create table restaurante (
       id bigint not null auto_increment,
        data_atualizacao datetime not null,
        data_cadastro datetime not null,
        endereco_bairro varchar(255),
        endereco_cep varchar(255),
        endereco_complemento varchar(255),
        endereco_logradouro varchar(255),
        endereco_numero varchar(255),
        nome varchar(255),
        taxa_frete decimal(19,2),
        cozinha_id bigint not null,
        endereco_cidade_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table restaurante_forma_pagamento (
       restaurante_id bigint not null,
        forma_pagamento_id bigint not null
    ) engine=InnoDB;

    create table usuario (
       id bigint not null auto_increment,
        data_cadastro datetime,
        email varchar(255) not null,
        nome varchar(255) not null,
        senha varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    alter table cidade 
       add constraint FKkworrwk40xj58kevvh3evi500 
       foreign key (estado_id) 
       references estado (id);

    alter table grupo_permissao 
       add constraint FKh21kiw0y0hxg6birmdf2ef6vy 
       foreign key (permissao_id) 
       references permissao (id);

    alter table grupo_permissao 
       add constraint FKta4si8vh3f4jo3bsslvkscc2m 
       foreign key (grupo_id) 
       references grupo (id);

    alter table grupo_usuario 
       add constraint FKsefl4bg3whol4iks53t2mgc9l 
       foreign key (grupo_id) 
       references grupo (id);

    alter table grupo_usuario 
       add constraint FKnoonyfhrgll54jvve2bhld4va 
       foreign key (usuario_id) 
       references usuario (id);

    alter table produto 
       add constraint FKb9jhjyghjcn25guim7q4pt8qx 
       foreign key (restaurante_id) 
       references restaurante (id);

    alter table restaurante 
       add constraint FK76grk4roudh659skcgbnanthi 
       foreign key (cozinha_id) 
       references cozinha (id);

    alter table restaurante 
       add constraint FKbc0tm7hnvc96d8e7e2ulb05yw 
       foreign key (endereco_cidade_id) 
       references cidade (id);

    alter table restaurante_forma_pagamento 
       add constraint FK7aln770m80358y4olr03hyhh2 
       foreign key (forma_pagamento_id) 
       references forma_pagamento (id);

    alter table restaurante_forma_pagamento 
       add constraint FKa30vowfejemkw7whjvr8pryvj 
       foreign key (restaurante_id) 
       references restaurante (id);
insert into cozinha (nome) values ('Amazonense');
insert into cozinha (nome) values ('Mineira');
insert into cozinha (nome) values ('Italiana');
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Cantinho do Burguer', 4.76, 2, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Vini burguer', 3.80, 2, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Paçai', 7.80, 1, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Forno da saudade', 1.15, 3, utc_timestamp, utc_timestamp);
insert into estado (nome) values ('Minas Gerais');
insert into estado (nome) values ('São Paulo');
insert into estado (nome) values ('Rio Grande do Sul');
insert into estado (nome) values ('Santa Catarina');
insert into cidade (nome, estado_id) values ('Campinas', 2);
insert into cidade (nome, estado_id) values ('Porto Alegre', 3);
insert into cidade (nome, estado_id) values ('Florianopolis', 4);
insert into cidade (nome, estado_id) values ('Belo Horizonte', 1);
insert into forma_pagamento (descricao) values ('Cartão de crédito');
insert into forma_pagamento (descricao) values ('Cartão de débito');
insert into forma_pagamento (descricao) values ('Pix');
insert into forma_pagamento (descricao) values ('Dinheiro');
insert into forma_pagamento (descricao) values ('Boleto');
insert into restaurante_forma_pagamento values (1,1), (1,2), (1,3), (1,4), (2,1), (2,2), (2,3), (3,2), (3,4), (4,5);
insert into produto (nome, preco, ativo, restaurante_id) values ("Super Cantinho", 33.33, true, 1);
insert into produto (nome, preco, ativo, restaurante_id) values ("Pizza italiana", 45.50, true, 4);
insert into produto (nome, preco, ativo, restaurante_id) values ("Especial da casa combo", 50, true, 2);
insert into produto (nome, preco, ativo, restaurante_id) values ("Açai completo", 15.99, true, 3);
insert into permissao (nome, descricao) values ("Vender", "Permissao para vender");
insert into permissao (nome, descricao) values ("Criar produto", "Permissao para criar");
insert into permissao (nome, descricao) values ("Deletar produto", "Permissao para deletar");
insert into permissao (nome, descricao) values ("Comprar", "Permissao para comprar");
insert into permissao (nome, descricao) values ("Cadastrar forma de pagamento", "Permissao para cadastrar forma de pagamento");
insert into permissao (nome, descricao) values ("Visualizar restaurante", "Permissao para visualizar restaurante");
insert into grupo (nome) values ("Vendedor");
insert into grupo (nome) values ("Admin");
insert into grupo (nome) values ("Consumidor");
insert into grupo (nome) values ("Visitante");
insert  into grupo_permissao values (1,1), (1,2), (1,4), (1,5), (1,6);
insert  into grupo_permissao values (2,1), (2,2), (2,3), (2,4), (2,5), (2,6);
insert  into grupo_permissao values (3,4), (3,5), (3,6);
insert  into grupo_permissao values (4,6);
insert into usuario (nome, email, senha) values ("Pedro", "pedro@gmail.com", "senhasenha");
insert into usuario (nome, email, senha) values ("Andressa", "andressa@gmail.com", "senhasenha123");
insert into usuario (nome, email, senha) values ("Noah", "noah@gmail.com", "broabroa");
insert into usuario (nome, email, senha) values ("Amora", "amora@gmail.com", "rozilda");
insert into grupo_usuario values (1,2), (2,1), (3,4), (4,3);
