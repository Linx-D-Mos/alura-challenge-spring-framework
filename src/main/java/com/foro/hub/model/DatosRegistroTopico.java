package com.foro.hub.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;

public record DatosRegistroTopico(
        @NotBlank @JsonAlias("titulo") String encabezadoPrincipal,
        @NotBlank @JsonAlias("mensaje") String contenidoCuerpo,
        @NotBlank @JsonAlias("autor") String creador,
        @NotBlank @JsonAlias("curso") String categoriaCurso
) {}