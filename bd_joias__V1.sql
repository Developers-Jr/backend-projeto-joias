create database pjoias;
use pjoias;

create table admin(
	id_admin bigint primary key auto_increment not null,
	nome_admin varchar(100) not null,
    sobrenome_admin varchar(255) not null,
    email_admin varchar(255) not null,
    senha_admin varchar(255) not null
);

create table vendedor(
	id_vendedor bigint primary key auto_increment not null,
    nome_vendedor varchar(100) not null,
    sobrenome_vendedor varchar(255) not null,
    email_vendedor varchar(255) not null,
    telefone_vendedor varchar(15) not null,
    senha_vendedor varchar(255) not null,
	id_admin bigint,
    constraint fk_admin
    foreign key(id_admin)
		references admin(id_admin)
);

create table revendedor(
	id_revendedor bigint primary key auto_increment not null,
	nome_revendedor varchar(100) not null,
    telefone_revendedor varchar(15) not null,
    cpf_revendedor varchar(20) not null,
    rg_revendedor varchar(20) not null,
    id_vendedor bigint,
    constraint fk_vendedor
    foreign key(id_vendedor)
		references vendedor(id_vendedor)
);

create table endereco(
	cod_endereco bigint primary key auto_increment not null,  
	estado varchar(100) not null,
    cidade varchar(100) not null,
    rua varchar(255) not null,
    numero varchar(10) not null,
    cep varchar(20) not null,
    id_revendedor bigint,
    constraint fk_revendedor
    foreign key(id_revendedor)
		references revendedor(id_revendedor)
);

create table historico(
	id_historico bigint primary key auto_increment not null,  
    id_vendedor bigint,
    constraint fk_vendedor_historico
    foreign key(id_vendedor)
		references vendedor(id_vendedor)
);

create table maleta_historico(
	id_historico bigint,
    constraint fk_id_historico  
    foreign key(id_historico) 
		references historico(id_historico),
    id_maleta bigint,
    constraint fk_id_maleta
    foreign key(id_maleta)
		references maleta(id_maleta),
	valor decimal not null default 0.00,
	primary key(id_historico, id_maleta)
);

create table maleta(
	id_maleta bigint primary key auto_increment not null, 
    nome_maleta varchar(100) not null,
    valor_maleta decimal not null default 0.00,
    status_maleta boolean default true,
    id_admin bigint,
    constraint fk_id_admin
    foreign key(id_admin)
		references admin(id_admin)
);

create table produto(
	id_produto bigint primary key auto_increment not null,  
    nome_produto varchar(100) not null,
    valor_produto decimal not null default 0.00,
    id_maleta bigint,
    constraint fk_id_maleta_produto
    foreign key(id_maleta)
		references maleta(id_maleta)
);

insert into admin(nome_admin, sobrenome_admin, email_admin, senha_admin) values ('caio', 'mello', 'caio.ds.2003@gmail.com', 'caio123');
select * from admin;

insert into vendedor(nome_vendedor, sobrenome_vendedor, email_vendedor, senha_vendedor, telefone_vendedor, id_admin) values ('sandro', 'mello', 'gi@gmail.com', 'gi123', '9888869', 1);
select * from vendedor;

insert into revendedor(nome_revendedor, telefone_revendedor, cpf_revendedor, rg_revendedor, id_vendedor) values ('sandro', '988120470', '458.722.218-60', '035.050.025-1', 1);
select * from revendedor;

insert into endereco(estado, cidade, rua, numero, cep, id_revendedor) values ('SP', 'hortolandia', 'celio vieira', '829', '13127-203', 1);
select * from endereco;

insert into historico(id_vendedor) values (2);
select * from historico;

insert into maleta(nome_maleta, valor_maleta, id_admin) values ('204', 10.00, 1);
select * from maleta;

insert into produto(nome_produto, valor_produto, id_maleta) values ('br', 101.00, 1);
select * from produto;

insert into maleta_historico(id_historico, id_maleta) values (2, 1);
select * from maleta_historico;


