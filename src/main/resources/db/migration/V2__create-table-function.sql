CREATE TABLE funcao(
 id varchar(100) not null PRIMARY KEY,
 name varchar(100) default null,
 icone varchar(100) default null,
 router varchar(100) default null,
 ativo boolean not null,
 menu_itens boolean not null,
 icone_item boolean not null
);