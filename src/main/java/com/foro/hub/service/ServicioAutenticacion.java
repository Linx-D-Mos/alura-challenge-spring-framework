package com.foro.hub.service;

import com.foro.hub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ServicioAutenticacion implements UserDetailsService {

    private final UsuarioRepository repositorioUsuarios;

    @Autowired
    public ServicioAutenticacion(UsuarioRepository repositorioUsuarios) {
        this.repositorioUsuarios = repositorioUsuarios;
    }

    @Override
    public UserDetails loadUserByUsername(String identificadorAcceso) throws UsernameNotFoundException {
        return repositorioUsuarios.findByCorreoElectronico(identificadorAcceso);
    }
}
