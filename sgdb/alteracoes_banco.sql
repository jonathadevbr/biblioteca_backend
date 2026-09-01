-- 31/08/2026

ALTER TABLE biblioteca.usuario RENAME CONSTRAINT uk5171l57faosmj8myawaucatdw TO uk_usuario_email;
ALTER TABLE biblioteca.usuario RENAME CONSTRAINT uk692bsnqxa8m9fmx7m1yc6hsui TO uk_usuario_cpf;
ALTER TABLE biblioteca.categoria RENAME CONSTRAINT ukprx5elpv558ah8pk8x18u56yc TO uk_categoria_nome;
ALTER TABLE biblioteca.livro RENAME CONSTRAINT ukk8si93wtslp275pv65gity1gg TO uk_livro_isbn;