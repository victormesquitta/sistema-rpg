package senac.domain.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.domain.dtos.requests.UsuarioRequestDTO;
import senac.domain.dtos.responses.UsuarioResponseDTO;
import senac.domain.mappers.UsuarioMapper;
import senac.domain.services.UsuarioService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @PostMapping
    public ResponseEntity<Object> criarUsuario(@RequestBody @Valid UsuarioRequestDTO usuarioRequestDto) {
        usuarioService.criarUsuario(usuarioRequestDto);
        return new ResponseEntity<>("Usuário criado com sucesso.", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> listarUsuarios() {
        List<UsuarioResponseDTO> listaRespostaUsuarios = usuarioService.listarUsuariosResponse();
        return ResponseEntity.ok(listaRespostaUsuarios);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> obterUsuarioPorId(@PathVariable Integer id) {
        UsuarioResponseDTO usuarioResponseDto = usuarioService.obterUsuarioPorIdResponse(id);
        return ResponseEntity.ok(usuarioResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarUsuario(@PathVariable Integer id, @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        usuarioService.atualizarUsuario(id, usuarioRequestDTO);
        return new ResponseEntity<>("Usuário atualizado com sucesso.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirUsuario(@PathVariable Integer id) {
        usuarioService.excluirUsuario(id);
        return new ResponseEntity<>("Usuário excluído com sucesso.", HttpStatus.OK);
    }
}
