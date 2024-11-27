package com.ghfelipe.cadastro.services;

import com.ghfelipe.cadastro.model.Usuario;
import com.ghfelipe.cadastro.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UsuarioService implements UserDetailsService {

    private Logger logger = Logger.getLogger(UsuarioService.class.getName());

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("finding one user by email" + username);
        var usuario = repository.findByEmail(username);
        if (usuario == null) {
            throw new UsernameNotFoundException(username + " not found!");
        }
        logger.info("Encontrou " + username + " !");

        return usuario;
    }
}
