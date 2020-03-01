CREATE SEQUENCE public.estado_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 2
  CACHE 1;
ALTER TABLE public.estado_id_seq
  OWNER TO postgres;
  
CREATE TABLE public.estado
(
  codigo bigint NOT NULL DEFAULT nextval('estado_id_seq'::regclass),
  nome character varying(50) NOT NULL,
  sigla character varying (2) NOT NULL,
  CONSTRAINT estado_pkey PRIMARY KEY (codigo)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.estado
  OWNER TO postgres;  
  
CREATE SEQUENCE public.cidade_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 2
  CACHE 1;
ALTER TABLE public.cidade_id_seq
  OWNER TO postgres;
  
CREATE TABLE public.cidade
(
  codigo bigint NOT NULL DEFAULT nextval('cidade_id_seq'::regclass),
  nome character varying(50) NOT NULL,
  codigo_estado bigint NOT NULL,
  CONSTRAINT cidade_pkey PRIMARY KEY (codigo),
  FOREIGN KEY (codigo_estado) REFERENCES estado(codigo)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.cidade
  OWNER TO postgres;
  
INSERT INTO estado (codigo, nome, sigla) VALUES(nextval('estado_id_seq'::regclass), 'ACRE', 'AC');
INSERT INTO estado (codigo, nome, sigla) VALUES(nextval('estado_id_seq'::regclass), 'BAHIA', 'BA');
INSERT INTO estado (codigo, nome, sigla) VALUES(nextval('estado_id_seq'::regclass), 'GOIAS', 'GO');
INSERT INTO estado (codigo, nome, sigla) VALUES(nextval('estado_id_seq'::regclass), 'MINAS GERAIS', 'MG');
INSERT INTO estado (codigo, nome, sigla) VALUES(nextval('estado_id_seq'::regclass), 'SANTA CATARINA', 'SC');
INSERT INTO estado (codigo, nome, sigla) VALUES(nextval('estado_id_seq'::regclass), 'SAO PAULO', 'SP');

INSERT INTO cidade (nome, codigo_estado) VALUES('RIO BRANCO', 2);
INSERT INTO cidade (nome, codigo_estado) VALUES('CRUZEIRO DO SUL', 2);
INSERT INTO cidade (nome, codigo_estado) VALUES('SALVADOR', 3);
INSERT INTO cidade (nome, codigo_estado) VALUES('PORTO SEGURO', 3);
INSERT INTO cidade (nome, codigo_estado) VALUES('SANTANA', 3);
INSERT INTO cidade (nome, codigo_estado) VALUES('GOIANIA', 4);
INSERT INTO cidade (nome, codigo_estado) VALUES('ITUMBIARA', 4);
INSERT INTO cidade (nome, codigo_estado) VALUES('NOVO BRASIL', 4);
INSERT INTO cidade (nome, codigo_estado) VALUES('BELO HORIZONTE', 5);
INSERT INTO cidade (nome, codigo_estado) VALUES('UBERLANDIA', 5);
INSERT INTO cidade (nome, codigo_estado) VALUES('MONTE CARLOS', 5);
INSERT INTO cidade (nome, codigo_estado) VALUES('FLORIANOPOLIS', 6);
INSERT INTO cidade (nome, codigo_estado) VALUES('CRICIUMA', 6);
INSERT INTO cidade (nome, codigo_estado) VALUES('CAMBORIU', 6);
INSERT INTO cidade (nome, codigo_estado) VALUES('LAGES', 6);
INSERT INTO cidade (nome, codigo_estado) VALUES('SAO PAULO', 7);
INSERT INTO cidade (nome, codigo_estado) VALUES('RIBEIRAO PRETO', 7);
INSERT INTO cidade (nome, codigo_estado) VALUES('CAMPINAS', 7);
INSERT INTO cidade (nome, codigo_estado) VALUES('SANTOS', 7);