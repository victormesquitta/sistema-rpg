package senac.domain.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import senac.domain.dtos.requests.AuthenticationDTO;
import senac.domain.models.UsuarioModel;
import senac.domain.repositories.UsuarioRepository;

@RestController
@RequestMapping("auth")

public class AuthenticationController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")

    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassoword = new UsernamePasswordAuthenticationToken(data.usuario(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassoword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/registrar")
    public ResponseEntity registrar(@RequestBody @Valid AuthenticationDTO data) {
        if (this.repository.findByLogin(data.usuario()) != null)                                    //caso já tenha um usuario no banco
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());

        UsuarioModel novoUsuario = new UsuarioModel(data.usuario(), encryptedPassword);

         this.repository.save(novoUsuario);

         return ResponseEntity.ok().build();
    }


}
