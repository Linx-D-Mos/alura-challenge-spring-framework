package com.foro.hub.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotNull Long id,
        @JsonAlias("titulo") String encabezadoPrincipal,
        @JsonAlias("mensaje") String contenidoCuerpo,
        @JsonAlias("autor") String creador,
        @JsonAlias("curso") String categoriaCurso,
        @JsonAlias("status") String estadoActual
) {}