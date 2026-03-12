package com.foro.hub.security;

import com.foro.hub.repository.UsuarioRepository;
import com.foro.hub.service.ServicioManejoTokens;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FiltroAutenticacionJwt extends OncePerRequestFilter {

    private final ServicioManejoTokens gestorTokens;
    private final UsuarioRepository repositorioUsuarios;

    @Autowired
    public FiltroAutenticacionJwt(ServicioManejoTokens gestorTokens, UsuarioRepository repositorioUsuarios) {
        this.gestorTokens = gestorTokens;
        this.repositorioUsuarios = repositorioUsuarios;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest peticionHTTP, HttpServletResponse respuestaHTTP, FilterChain cadenaFiltros) 
            throws ServletException, IOException {
        
        String cabeceraAutorizacion = peticionHTTP.getHeader("Authorization");
        
        if (cabeceraAutorizacion != null && cabeceraAutorizacion.startsWith("Bearer ")) {
            String tokenExtraido = cabeceraAutorizacion.replace("Bearer ", "");
            String identificadorUsuario = gestorTokens.extraerAsuntoDelToken(tokenExtraido);
            
            if (identificadorUsuario != null) {
                UserDetails usuarioEncontrado = repositorioUsuarios.findByCorreoElectronico(identificadorUsuario);
                
                if(usuarioEncontrado != null) {
                    var sesionAutorizada = new UsernamePasswordAuthenticationToken(
                        usuarioEncontrado, 
                        null, 
                        usuarioEncontrado.getAuthorities()
                    );
                    SecurityContextHolder.getContext().setAuthentication(sesionAutorizada);
                }
            }
        }
        cadenaFiltros.doFilter(peticionHTTP, respuestaHTTP);
    }
}
