package com.alura.modelo;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        String titulo,
        String mensaje,
        LocalDateTime fecha_creacion,
        String nombreAutor,
        Long cursoId
) {
}
