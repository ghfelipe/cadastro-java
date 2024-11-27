package com.ghfelipe.cadastro.controller;

import com.ghfelipe.cadastro.model.Usuario;
import com.ghfelipe.cadastro.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ListarUsuariosController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping(value = "/m/usuario")
    public List<Usuario> get() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios;
    }

}
