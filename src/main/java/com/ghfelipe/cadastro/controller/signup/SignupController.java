package com.ghfelipe.cadastro.controller.signup;

import com.ghfelipe.cadastro.model.Perfil;
import com.ghfelipe.cadastro.model.Usuario;
import com.ghfelipe.cadastro.repositories.PerfilRepository;
import com.ghfelipe.cadastro.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class SignupController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity post(@RequestBody SignupRequestVO signupRequestVO) {
        String email = signupRequestVO.getUsername();
        String senha = signupRequestVO.getPassword();
        String senhaHash = passwordEncoder.encode(senha);

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senhaHash);
        usuarioRepository.save(usuario);

        Usuario usuarioPersistido = usuarioRepository.findByEmail(email);

        Perfil perfil = perfilRepository.findByNome("USER");
        List<Perfil> permissao = new ArrayList<>();
        permissao.add(perfil);

        usuarioPersistido.setPermissoes(permissao);
        usuarioRepository.save(usuarioPersistido);

        return ResponseEntity.ok().build();
    }
}
