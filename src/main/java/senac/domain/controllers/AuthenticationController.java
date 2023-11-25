package senac.domain.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import senac.domain.dtos.requests.AuthenticationDTO;
import senac.domain.models.UsuarioModel;
import senac.domain.repositories.UsuarioRepository;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UsuarioModel usuarioModel;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")

    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authentication) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(authentication.usuario(), authentication.senha());
        this.authenticationManager.authenticate(authenticationToken);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/registrar")
    public ResponseEntity registrar(@RequestBody @Valid AuthenticationDTO authentication) {
        if (usuarioRepository.findByUsuario(authentication.usuario()) != null){
            //caso já tenha um usuario no banco
            return ResponseEntity.badRequest().build();
        }
        usuarioModel.setUsuario(authentication.usuario());
        usuarioModel.setSenha(passwordEncoder.encode(authentication.senha()));
        usuarioRepository.save(usuarioModel);
        return ResponseEntity.ok().build();
    }


}
