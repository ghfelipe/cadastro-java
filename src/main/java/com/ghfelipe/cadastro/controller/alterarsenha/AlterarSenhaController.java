package com.ghfelipe.cadastro.controller.alterarsenha;

import com.ghfelipe.cadastro.model.Usuario;
import com.ghfelipe.cadastro.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlterarSenhaController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/auth/alterarSenha")
    public ResponseEntity get(@RequestBody AlterarSenhaRequestVO alterarSenhaRequestVO) {

        Usuario usuario = usuarioRepository.findByEmail(alterarSenhaRequestVO.getUsername());

        if (usuario == null) {
            return ResponseEntity.badRequest().body("Usuário não encontrado");
        }

        if (!alterarSenhaRequestVO.getCodigo().equals(usuario.getCodigo())) {
            return ResponseEntity.badRequest().body("Código inválido");
        }

        String senha = alterarSenhaRequestVO.getSenha();
        String senhaEncriptada = passwordEncoder.encode(senha);
        usuario.setSenha(senhaEncriptada);
        usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
    }
}
