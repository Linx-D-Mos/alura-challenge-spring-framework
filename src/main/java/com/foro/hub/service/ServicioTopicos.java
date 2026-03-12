package com.foro.hub.service;

import com.foro.hub.model.DatosActualizarTopico;
import com.foro.hub.model.DatosRegistroTopico;
import com.foro.hub.model.DatosRespuestaTopico;
import com.foro.hub.model.Topico;
import com.foro.hub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicioTopicos {

    private final TopicoRepository repositorioTopicos;

    @Autowired
    public ServicioTopicos(TopicoRepository repositorioTopicos) {
        this.repositorioTopicos = repositorioTopicos;
    }

    public boolean existeRegistroDuplicado(DatosRegistroTopico informacionEntrante) {
        return repositorioTopicos.existsByEncabezadoPrincipalAndContenidoCuerpo(
                informacionEntrante.encabezadoPrincipal(), 
                informacionEntrante.contenidoCuerpo()
        );
    }

    public Topico almacenarNuevoRegistro(DatosRegistroTopico informacionEntrante) {
        Topico nuevoElemento = new Topico(informacionEntrante);
        return repositorioTopicos.save(nuevoElemento);
    }

    public Page<DatosRespuestaTopico> obtenerListadoPaginado(Pageable parametrosPaginado) {
        return repositorioTopicos.findAll(parametrosPaginado).map(DatosRespuestaTopico::new);
    }

    public DatosRespuestaTopico obtenerDetallePorReferencia(Long identificador) {
        Topico registro = repositorioTopicos.getReferenceById(identificador);
        return new DatosRespuestaTopico(registro);
    }
    
    public Optional<Topico> buscarPorIdentificador(Long identificador) {
        return repositorioTopicos.findById(identificador);
    }

    public boolean eliminarRegistroConExistencia(Long identificador) {
        if (repositorioTopicos.existsById(identificador)) {
            repositorioTopicos.deleteById(identificador);
            return true;
        }
        return false;
    }
}
