create table forma_pagamento
(
    id        bigint       not null auto_increment,
    descricao varchar(40) not null,
    primary key (id)
) engine = InnoDB DEFAULT CHARSET=utf8;

create table grupo
(
    id   bigint       not null auto_increment,
    nome varchar(60) not null,
    primary key (id)
) engine = InnoDB DEFAULT CHARSET=utf8;

create table grupo_permissao
(
    grupo_id     bigint not null,
    permissao_id bigint not null
) engine = InnoDB DEFAULT CHARSET=utf8;

create table grupo_usuario
(
    usuario_id bigint not null,
    grupo_id   bigint not null
) engine = InnoDB DEFAULT CHARSET=utf8;

create table permissao
(
    id        bigint       not null auto_increment,
    descricao varchar(60) not null,
    nome      varchar(40) not null,
    primary key (id)
) engine = InnoDB DEFAULT CHARSET=utf8;

create table produto
(
    id             bigint not null auto_increment,
    ativo          bit    not null,
    nome           varchar(80),
    preco          decimal(4, 2),
    restaurante_id bigint not null,
    primary key (id)
) engine = InnoDB DEFAULT CHARSET=utf8;

create table restaurante
(
    id                   bigint   not null auto_increment,
    data_atualizacao     datetime not null,
    data_cadastro        datetime not null,
    endereco_bairro      varchar(40),
    endereco_cep         varchar(8),
    endereco_complemento varchar(80),
    endereco_logradouro  varchar(100),
    endereco_numero      varchar(6),
    nome                 varchar(60),
    taxa_frete           decimal(4, 2),
    cozinha_id           bigint   not null,
    endereco_cidade_id   bigint,
    primary key (id)
) engine = InnoDB DEFAULT CHARSET=utf8;

create table restaurante_forma_pagamento
(
    restaurante_id     bigint not null,
    forma_pagamento_id bigint not null
) engine = InnoDB DEFAULT CHARSET=utf8;

create table usuario
(
    id            bigint       not null auto_increment,
    data_cadastro datetime,
    email         varchar(60) not null,
    nome          varchar(60) not null,
    senha         varchar(40) not null,
    primary key (id)
) engine = InnoDB DEFAULT CHARSET=utf8;

alter table grupo_permissao
    add constraint grupo_permissao_permissao_id
        foreign key (permissao_id)
            references permissao (id);

alter table grupo_permissao
    add constraint grupo_permissao_grupo_id
        foreign key (grupo_id)
            references grupo (id);

alter table grupo_usuario
    add constraint grupo_usuario_grupo_id
        foreign key (grupo_id)
            references grupo (id);

alter table grupo_usuario
    add constraint grupo_usuario_usuario_id
        foreign key (usuario_id)
            references usuario (id);

alter table produto
    add constraint produto_restaurante
        foreign key (restaurante_id)
            references restaurante (id);

alter table restaurante
    add constraint restaurante_cozinha
        foreign key (cozinha_id)
            references cozinha (id);

alter table restaurante
    add constraint restaurante_cidade
        foreign key (endereco_cidade_id)
            references cidade (id);

alter table restaurante_forma_pagamento
    add constraint restaurante_forma_pagamento_forma_pagamento_id
        foreign key (forma_pagamento_id)
            references forma_pagamento (id);

alter table restaurante_forma_pagamento
    add constraint restaurante_forma_pagamento_restaurante_id
        foreign key (restaurante_id)
            references restaurante (id);
