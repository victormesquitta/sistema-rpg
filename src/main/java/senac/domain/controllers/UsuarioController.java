package senac.domain.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.domain.dtos.UsuarioDTO;
import senac.domain.dtos.UsuarioRespostaDTO;
import senac.domain.services.UsuarioService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Object> criarUsuario(@RequestBody @Valid UsuarioDTO usuarioDto) {
        usuarioService.criarUsuario(usuarioDto);
        return new ResponseEntity<>("Usuário criado com sucesso.", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> listarUsuarios() {
        List<UsuarioRespostaDTO> listaRespostaUsuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(listaRespostaUsuarios);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> obterUsuarioPorId(@PathVariable Integer id) {
        UsuarioRespostaDTO usuarioRespostaDto = usuarioService.obterUsuarioPorId(id);
        return ResponseEntity.ok(usuarioRespostaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarUsuario(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO) {
        usuarioService.obterUsuarioPorId(id);
        usuarioService.atualizarUsuario(id, usuarioDTO);
        return new ResponseEntity<>("Usuário atualizado com sucesso.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirUsuario(@PathVariable Integer id) {
        usuarioService.obterUsuarioPorId(id);
        usuarioService.excluirUsuario(id);
        return new ResponseEntity<>("Usuário excluído com sucesso.", HttpStatus.OK);
    }
}
