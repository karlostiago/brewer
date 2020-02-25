CREATE SEQUENCE public.estilo_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 2
  CACHE 1;
ALTER TABLE public.estilo_id_seq
  OWNER TO postgres;
  
CREATE TABLE public.estilo
(
  codigo bigint NOT NULL DEFAULT nextval('estilo_id_seq'::regclass),
  nome character varying(50) NOT NULL,
  CONSTRAINT estilo_pkey PRIMARY KEY (codigo)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.estilo
  OWNER TO postgres;
  
INSERT INTO estilo VALUES(1, 'Amber Lager');
INSERT INTO estilo VALUES(2, 'Dark Lager');
INSERT INTO estilo VALUES(3, 'Pale Lager');
INSERT INTO estilo VALUES(4, 'Pilsner');
  
CREATE SEQUENCE public.cerveja_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 2
  CACHE 1;
ALTER TABLE public.cerveja_id_seq
  OWNER TO postgres;
  
CREATE TABLE public.cerveja
(
	codigo bigint NOT NULL DEFAULT nextval('cerveja_id_seq'::regclass),
	sku character varying(50) NOT NULL,
	nome character varying(80) NOT NULL,
	descricao text NOT NULL,
	valor decimal(10, 2) NOT NULL,
	teor_alcoolico decimal(10, 2) NOT NULL,
	comissao decimal(10, 2) NOT NULL,
	sabor character varying(50) NOT NULL,
	origem character varying(50) NOT NULL,
	codigo_estilo bigint NOT NULL,
	FOREIGN KEY (codigo_estilo) REFERENCES estilo(codigo)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.cerveja
  OWNER TO postgres;
  