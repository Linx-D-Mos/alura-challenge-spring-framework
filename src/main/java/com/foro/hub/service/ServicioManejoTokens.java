package com.foro.hub.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.foro.hub.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class ServicioManejoTokens {

    @Value("${api.security.secret}")
    private String llaveSecreta;

    public String emitirTokenJwt(Usuario credencialesUsuario) {
        try {
            Algorithm metodoCifrado = Algorithm.HMAC256(llaveSecreta);
            return JWT.create()
                    .withIssuer("Foro Hub")
                    .withSubject(credencialesUsuario.getCorreoElectronico())
                    .withExpiresAt(calcularExpiracion())
                    .sign(metodoCifrado);
        } catch (JWTCreationException problemaDeFirma){
            throw new RuntimeException("Ocurrió un error al emitir el token de seguridad", problemaDeFirma);
        }
    }

    public String extraerAsuntoDelToken(String tokenGenerado) {
        if (tokenGenerado == null) { throw new RuntimeException("El token es nulo"); }
        try {
            Algorithm metodoCifrado = Algorithm.HMAC256(llaveSecreta);
            return JWT.require(metodoCifrado)
                    .withIssuer("Foro Hub")
                    .build()
                    .verify(tokenGenerado)
                    .getSubject();
        } catch (JWTVerificationException excepcionValidacion) {
            throw new RuntimeException("El token proporcionado no es válido o ha expirado");
        }
    }

    private Instant calcularExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
