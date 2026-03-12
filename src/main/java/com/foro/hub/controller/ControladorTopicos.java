package com.foro.hub.controller;

import com.foro.hub.model.*;
import com.foro.hub.service.ServicioTopicos;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class ControladorTopicos {

    private final ServicioTopicos servicioTopicos;

    @Autowired
    public ControladorTopicos(ServicioTopicos servicioTopicos) {
        this.servicioTopicos = servicioTopicos;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> registrarPublicacion(@RequestBody @Valid DatosRegistroTopico cargaUtil, UriComponentsBuilder contructorUris) {
        if (servicioTopicos.existeRegistroDuplicado(cargaUtil)) {
            return ResponseEntity.badRequest().body("Inconveniente: El tópico enviado ya existe en la base de datos.");
        }
        
        Topico registroInsertado = servicioTopicos.almacenarNuevoRegistro(cargaUtil);
        URI localizadorRecurso = contructorUris.path("/topicos/{id}").buildAndExpand(registroInsertado.getId()).toUri();
        
        return ResponseEntity.created(localizadorRecurso).body(new DatosRespuestaTopico(registroInsertado));
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaTopico>> obtenerListado(
            @PageableDefault(size = 10, sort = "instanteRegistro") Pageable parametrosPaginado) {
        
        Page<DatosRespuestaTopico> resultadosPagina = servicioTopicos.obtenerListadoPaginado(parametrosPaginado);
        return ResponseEntity.ok(resultadosPagina);
    }

    @GetMapping("/{idTopico}")
    public ResponseEntity<DatosRespuestaTopico> verDetalleRegistro(@PathVariable Long idTopico) {
        DatosRespuestaTopico informacionDetallada = servicioTopicos.obtenerDetallePorReferencia(idTopico);
        return ResponseEntity.ok(informacionDetallada);
    }

    @DeleteMapping("/{idTopico}")
    @Transactional
    public ResponseEntity<Object> borrarPublicacion(@PathVariable Long idTopico) {
        boolean fueEliminado = servicioTopicos.eliminarRegistroConExistencia(idTopico);
        
        if (fueEliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
