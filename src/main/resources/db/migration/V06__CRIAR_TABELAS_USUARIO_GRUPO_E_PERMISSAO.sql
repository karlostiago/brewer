CREATE SEQUENCE public.usuario_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 2
  CACHE 1;
ALTER TABLE public.usuario_id_seq
  OWNER TO postgres;
  
CREATE TABLE public.usuario
(
  codigo bigint NOT NULL DEFAULT nextval('usuario_id_seq'::regclass),
  nome character varying(80) NOT NULL,
  email character varying (80) NOT NULL,
  senha character varying (120) NOT NULL,
  ativo boolean default true,
  data_nascimento date,
  CONSTRAINT usuario_pkey PRIMARY KEY (codigo)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.usuario
  OWNER TO postgres;  
  
CREATE SEQUENCE public.grupo_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 2
  CACHE 1;
ALTER TABLE public.grupo_id_seq
  OWNER TO postgres;
  
CREATE TABLE public.grupo
(
  codigo bigint NOT NULL DEFAULT nextval('grupo_id_seq'::regclass),
  nome character varying(80) NOT NULL,
  CONSTRAINT grupo_pkey PRIMARY KEY (codigo)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.grupo
  OWNER TO postgres; 
  
CREATE SEQUENCE public.permissao_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 2
  CACHE 1;
ALTER TABLE public.permissao_id_seq
  OWNER TO postgres;
  
CREATE TABLE public.permissao
(
  codigo bigint NOT NULL DEFAULT nextval('permissao_id_seq'::regclass),
  nome character varying(80) NOT NULL,
  CONSTRAINT permissao_pkey PRIMARY KEY (codigo)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.permissao
  OWNER TO postgres; 
  
CREATE TABLE public.usuario_grupo
(
	codigo_usuario bigint NOT NULL,
	codigo_grupo bigint NOT NULL,
	CONSTRAINT usuario_grupo_pkey PRIMARY KEY (codigo_usuario, codigo_grupo),
	FOREIGN KEY (codigo_usuario) REFERENCES usuario(codigo),
	FOREIGN KEY (codigo_grupo) REFERENCES grupo(codigo)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.usuario_grupo
  OWNER TO postgres;