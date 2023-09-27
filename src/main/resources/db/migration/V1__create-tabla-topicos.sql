create table topicos (
    id INT AUTO_INCREMENT,
    titulo VARCHAR(200) NOT NULL,
    mensaje VARCHAR(200),
    fecha_creacion DATE NOT NULL,
    estatus VARCHAR(50) NOT NULL,
    autor_id INT NOT NULL,
    curso_id INT NOT NULL,
    PRIMARY KEY(id));