-- 31/08/2026

ALTER TABLE biblioteca.usuario RENAME CONSTRAINT uk5171l57faosmj8myawaucatdw TO uk_usuario_email;
ALTER TABLE biblioteca.usuario RENAME CONSTRAINT uk692bsnqxa8m9fmx7m1yc6hsui TO uk_usuario_cpf;
ALTER TABLE biblioteca.categoria RENAME CONSTRAINT ukprx5elpv558ah8pk8x18u56yc TO uk_categoria_nome;
ALTER TABLE biblioteca.livro RENAME CONSTRAINT ukk8si93wtslp275pv65gity1gg TO uk_livro_isbn;


-- 04/09/2026
ALTER TABLE biblioteca.emprestimo RENAME CONSTRAINT "fks8lirup1wisehyym648mgb5qg" TO fk_emprestimo_usuario;
ALTER TABLE biblioteca.emprestimo_livro RENAME CONSTRAINT "fkr0jfr0dhocnhjqbsnv49epli8" TO fk_emprestimo_livro_emprestimo;
ALTER TABLE biblioteca.emprestimo_livro RENAME CONSTRAINT "fka1v52elpg6k3igp0auri50346" TO fk_emprestimo_livro_livro;
ALTER TABLE biblioteca.livro RENAME CONSTRAINT "fksytxfd70p7l51i2cwndj1d21m" TO fk_livro_categoria;
ALTER TABLE biblioteca.livro_autor RENAME CONSTRAINT "fkimpe0j7022c82yftml9oroj5g" TO fk_livro_autor_livro;
ALTER TABLE biblioteca.livro_autor RENAME CONSTRAINT "fkgyu6d0f3ittiofr9ukke29lkr" TO fk_livro_autor_autor;