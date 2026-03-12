package com.foro.hub.model;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        String status,
        String autor,
        String curso,
        LocalDateTime fechaCreacion
) {
    public DatosRespuestaTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getStatus(), topico.getAutor(), topico.getCurso(),
                topico.getFechaCreacion());
    }
}