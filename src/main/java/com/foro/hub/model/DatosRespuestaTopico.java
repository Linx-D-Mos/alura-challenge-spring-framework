package com.foro.hub.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        @JsonProperty("titulo") String encabezadoPrincipal,
        @JsonProperty("mensaje") String contenidoCuerpo,
        @JsonProperty("status") String estadoActual,
        @JsonProperty("autor") String creador,
        @JsonProperty("curso") String categoriaCurso,
        @JsonProperty("fechaCreacion") LocalDateTime instanteRegistro
) {
    public DatosRespuestaTopico(Topico registroTopico) {
        this(
            registroTopico.getId(), 
            registroTopico.getEncabezadoPrincipal(), 
            registroTopico.getContenidoCuerpo(),
            registroTopico.getEstadoActual(), 
            registroTopico.getCreador(), 
            registroTopico.getCategoriaCurso(),
            registroTopico.getInstanteRegistro()
        );
    }
}