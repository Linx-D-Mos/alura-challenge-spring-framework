package com.foro.hub.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "titulo")
    private String encabezadoPrincipal;
    
    @Column(name = "mensaje")
    private String contenidoCuerpo;
    
    @Column(name = "fecha_creacion")
    private LocalDateTime instanteRegistro = LocalDateTime.now();
    
    @Column(name = "status")
    private String estadoActual = "ACTIVO";
    
    @Column(name = "autor")
    private String creador;
    
    @Column(name = "curso")
    private String categoriaCurso;

    public Topico() {}

    public Topico(DatosRegistroTopico informacionEntrante) {
        this.encabezadoPrincipal = informacionEntrante.encabezadoPrincipal();
        this.contenidoCuerpo = informacionEntrante.contenidoCuerpo();
        this.creador = informacionEntrante.creador();
        this.categoriaCurso = informacionEntrante.categoriaCurso();
    }

    public void actualizarInformacion(DatosActualizarTopico modificaciones) {
        if (modificaciones.encabezadoPrincipal() != null) this.encabezadoPrincipal = modificaciones.encabezadoPrincipal();
        if (modificaciones.contenidoCuerpo() != null) this.contenidoCuerpo = modificaciones.contenidoCuerpo();
        if (modificaciones.creador() != null) this.creador = modificaciones.creador();
        if (modificaciones.categoriaCurso() != null) this.categoriaCurso = modificaciones.categoriaCurso();
        if (modificaciones.estadoActual() != null) this.estadoActual = modificaciones.estadoActual();
    }

    public Long getId() { return id; }
    public String getEncabezadoPrincipal() { return encabezadoPrincipal; }
    public String getContenidoCuerpo() { return contenidoCuerpo; }
    public LocalDateTime getInstanteRegistro() { return instanteRegistro; }
    public String getEstadoActual() { return estadoActual; }
    public String getCreador() { return creador; }
    public String getCategoriaCurso() { return categoriaCurso; }
}