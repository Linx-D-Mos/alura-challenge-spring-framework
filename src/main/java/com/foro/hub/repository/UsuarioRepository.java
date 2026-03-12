package com.foro.hub.repository;

import com.foro.hub.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Este método es el que usará Spring Security para el login
    UserDetails findByEmail(String email);
}