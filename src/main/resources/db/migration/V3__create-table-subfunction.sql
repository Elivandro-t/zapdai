CREATE TABLE subfuncao(
 id varchar(100) not null PRIMARY KEY,
 name varchar(100) default null,
 router varchar(100) default null,
 icone varchar(100) default null,
 funcao_id varchar(100) not null,
  CONSTRAINT fk_funcao FOREIGN KEY (funcao_id) REFERENCES funcao(id)

);