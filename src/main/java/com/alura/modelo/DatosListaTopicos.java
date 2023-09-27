package com.alura.modelo;

import java.time.LocalDateTime;

public record DatosListaTopicos(String titulo, String mensaje, LocalDateTime fecha_creacion, StatusTopico estatus, String autor, Long curso) {

    public DatosListaTopicos(Topico topico) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getfechaCreacion(), topico.getStatus(), topico.getAutor().getNombre(), topico.getCurso().getId());
    }

}
