package senac.domain.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import senac.domain.dtos.requests.AuthenticationDTO;
import senac.domain.dtos.responses.LoginResponseDTO;
import senac.domain.models.UsuarioModel;
import senac.domain.repositories.UsuarioRepository;
import senac.domain.security.TokenService;

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

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")

    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authentication) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(authentication.usuario(), authentication.senha());
        var auth = this.authenticationManager.authenticate(authenticationToken);

        var token = tokenService.GerarToken((UsuarioModel) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }



}
