CREATE TABLE public.grupo_permissao
(
	codigo_grupo bigint NOT NULL,
	codigo_permissao bigint NOT NULL,
	CONSTRAINT grupo_permissao_pkey PRIMARY KEY (codigo_grupo, codigo_permissao),
	FOREIGN KEY (codigo_grupo) REFERENCES grupo(codigo),
	FOREIGN KEY (codigo_permissao) REFERENCES permissao(codigo)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.grupo_permissao
  OWNER TO postgres;