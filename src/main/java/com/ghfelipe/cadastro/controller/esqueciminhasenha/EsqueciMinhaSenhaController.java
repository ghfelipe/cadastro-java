package com.ghfelipe.cadastro.controller.esqueciminhasenha;

import com.ghfelipe.cadastro.model.Usuario;
import com.ghfelipe.cadastro.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Random;

@RestController
public class EsqueciMinhaSenhaController {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/auth/esqueciMinhaSenha")
    public ResponseEntity get(@RequestBody EsqueciMinhaSenhaRequestVO esqueciMinhaSenhaRequestVO) {

        Usuario usuario = usuarioRepository.findByEmail(esqueciMinhaSenhaRequestVO.getUsername());

        if (usuario == null) {
            return ResponseEntity.badRequest().build();
        }

        Random random = new Random();
        int low = 100000;
        int high = 999999;
        int codigo = random.nextInt(high-low) + low;

        usuario.setCodigo(String.valueOf(codigo));
        usuarioRepository.save(usuario);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ghfelipe.work@gmail.com");
        message.setTo(usuario.getEmail());
        message.setSubject("teste de email " + LocalDateTime.now());
        message.setText("Seu código é " + codigo + " !");
        emailSender.send(message);

        return ResponseEntity.ok().build();
    }
}
