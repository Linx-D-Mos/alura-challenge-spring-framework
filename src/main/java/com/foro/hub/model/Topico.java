package com.foro.hub.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private String status = "ACTIVO";
    private String autor;
    private String curso;

    // Constructor Vacío (Obligatorio para JPA)
    public Topico() {}

    // Constructor para registrar nuevo
    public Topico(DatosRegistroTopico datos) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.autor = datos.autor();
        this.curso = datos.curso();
    }

    public void actualizarDatos(DatosActualizarTopico datos) {
        if (datos.titulo() != null) this.titulo = datos.titulo();
        if (datos.mensaje() != null) this.mensaje = datos.mensaje();
        if (datos.autor() != null) this.autor = datos.autor();
        if (datos.curso() != null) this.curso = datos.curso();
        if (datos.status() != null) this.status = datos.status();
    }

    // --- GETTERS (Cópialos tal cual) ---
    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getMensaje() { return mensaje; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public String getStatus() { return status; }
    public String getAutor() { return autor; }
    public String getCurso() { return curso; }
}