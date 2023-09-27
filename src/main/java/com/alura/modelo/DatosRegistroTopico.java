package com.alura.modelo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
        @NotBlank
    String titulo,
        @NotBlank
    String mensaje,
    @NotNull
    Long autor,
    @NotNull
    Long curso){

}
