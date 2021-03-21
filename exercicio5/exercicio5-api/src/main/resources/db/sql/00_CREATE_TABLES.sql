create schema if not exists tinnova;

create table tinnova.marca (
	id smallserial,
	marca varchar(30),
	CONSTRAINT marca_PK PRIMARY KEY (id)
);

create table tinnova.veiculo (
	id bigserial,
	veiculo varchar(100),
	marca_id smallint,
	ano int,
	descricao text,
	vendido boolean,
	created timestamp,
	updated timestamp,	
	CONSTRAINT veiculo_PK PRIMARY KEY (id),
	CONSTRAINT veiculo_marca_FK FOREIGN KEY (marca_id) references tinnova.marca(id)
		ON DELETE NO ACTION ON UPDATE NO ACTION
);

