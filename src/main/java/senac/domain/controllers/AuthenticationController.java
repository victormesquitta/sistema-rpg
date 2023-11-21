package senac.domain.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import senac.domain.dtos.requests.AuthenticationDTO;

@RestController
@RequestMapping("auth")

public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/usuario")

    public ResponseEntity usuario (@RequestBody @Valid AuthenticationDTO data){
        var usernamePassoword = new UsernamePasswordAuthenticationToken(data.usuario(),data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassoword);

        return ResponseEntity.ok().build();
    }

}
