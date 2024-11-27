package com.ghfelipe.cadastro.controller;

import com.ghfelipe.cadastro.model.Usuario;
import com.ghfelipe.cadastro.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class QuemSouEuController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/u/eu")
    public ResponseEntity get(Principal principal) {
        String email = principal.getName();
        Usuario usuario = usuarioRepository.findByEmail(email);
        return ResponseEntity.ok(usuario);
    }
}
