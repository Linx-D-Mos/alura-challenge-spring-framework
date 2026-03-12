package com.foro.hub.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosAutenticacionUsuario(
    @JsonAlias("email") String correoUsuario, 
    @JsonAlias("contrasena") String claveSecreta
) {}