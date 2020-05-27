create database pjoias;
use pjoias;

create table admin(
	id bigint primary key auto_increment not null,
    nome varchar(100) not null,
	email varchar(255) not null,
    senha varchar(255) not null,
    sobrenome varchar(255) not null
);

create table vendedor(
	id bigint primary key auto_increment not null,
    nome varchar(100) not null,
    email varchar(255) not null,
    sobrenome varchar(255) not null,
    senha varchar(255) not null,
    telefone varchar(15) not null,
	id_admin bigint not null,
    constraint fk_admin
    foreign key(id_admin)
		references admin(id)
);

create table revendedor(
	id bigint primary key auto_increment not null,
	nome varchar(100) not null,
    telefone varchar(15) not null,
    cpf varchar(20) not null,
    rg varchar(20) not null,
    id_vendedor bigint not null,
    constraint fk_vendedor
    foreign key(id_vendedor)
		references vendedor(id)
);

create table endereco(
	cod_endereco bigint primary key auto_increment not null,  
	estado varchar(100) not null,
    cidade varchar(100) not null,
    rua varchar(255) not null,
    numero varchar(10) not null,
    cep varchar(20) not null,
    id_revendedor bigint not null,
    constraint fk_revendedor
    foreign key(id_revendedor)
		references revendedor(id)
);

create table historico(
	id bigint primary key auto_increment not null,  
    id_vendedor bigint not null,
    constraint fk_vendedor_historico
    foreign key(id_vendedor)
		references vendedor(id)
);

create table maleta_historico(
	id_historico bigint not null,
    constraint fk_id_historico  
    foreign key(id_historico) 
		references historico(id),
    id_maleta bigint not null,
    constraint fk_id_maleta
    foreign key(id_maleta)
		references maleta(id),
	valor decimal not null default 0.00,
	primary key(id_historico, id_maleta)
);

create table maleta(
	id bigint primary key auto_increment not null, 
    nome varchar(100) not null,
    valor decimal not null default 0.00,
    status_maleta boolean default true,
    id_admin bigint not null,
    constraint fk_id_admin
    foreign key(id_admin)
		references admin(id)
);

create table produto(
	id bigint primary key auto_increment not null,  
    nome varchar(100) not null,
    valor decimal not null default 0.00,
    id_maleta bigint not null,
    constraint fk_id_maleta_produto
    foreign key(id_maleta)
		references maleta(id)
);

select * from pjoias_user u;
select * from vendedor;
select * from admin;
insert into pjoias_user(nome_user, email_user, senha_user, isAdmin) values ('caio', 'caio.ds.2003@gmail.com', '123456', true);
insert into admin(sobrenome, id_user) values ('Mello', 1);
insert into admin(sobrenome, id_user) values ('Oliveira', 1);
