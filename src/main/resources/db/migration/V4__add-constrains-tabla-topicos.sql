alter table topicos add (constraint fk_autor_id foreign key(autor_id) references usuarios(id),
                        constraint fk_curso_id foreign key(curso_id) references cursos(id),
                        constraint unique_values unique(titulo, mensaje));