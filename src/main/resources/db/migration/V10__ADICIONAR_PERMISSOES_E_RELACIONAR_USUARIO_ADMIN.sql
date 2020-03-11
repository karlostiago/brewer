INSERT INTO permissao (codigo, nome) VALUES(1, 'CADASTRAR_CIDADE');
INSERT INTO permissao (codigo, nome) VALUES(2, 'CADASTRAR_USUARIO');

INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES(1, 1);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES(1, 2);

INSERT INTO usuario_grupo (codigo_usuario, codigo_grupo) VALUES(
	(SELECT codigo FROM usuario WHERE email = UPPER('carlostiagodesousa@gmail.com')), 1);