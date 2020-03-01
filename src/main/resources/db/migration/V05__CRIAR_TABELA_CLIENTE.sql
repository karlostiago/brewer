CREATE SEQUENCE public.cliente_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 2
  CACHE 1;
ALTER TABLE public.cliente_id_seq
  OWNER TO postgres;
  
CREATE TABLE public.cliente
(
  codigo bigint NOT NULL DEFAULT nextval('cliente_id_seq'::regclass),
  nome character varying(80) NOT NULL,
  tipo_pessoa character varying (15) NOT NULL,
  cpf_cnpj character varying (30),
  telefone character varying (20),
  email character varying (50) NOT NULL,
  logradouro character varying (50),
  numero character varying (15),
  complemento character varying (20),
  cep character varying (20),
  codigo_cidade bigint NOT NULL,
  CONSTRAINT cliente_pkey PRIMARY KEY (codigo),
  FOREIGN KEY (codigo_cidade) REFERENCES cidade(codigo)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.cliente
  OWNER TO postgres;  