package senac.domain.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import senac.domain.dtos.UsuarioRecordDto;
import senac.domain.models.UsuarioModel;
import senac.domain.repositories.UsuarioRepository;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<UsuarioModel> getAllUsers(){
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public UsuarioModel findUserByID(@PathVariable Integer id){
        return usuarioRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado"));
    }

//    @PostMapping()
//    @ResponseStatus(HttpStatus.CREATED)
//    public UsuarioModel save(@RequestBody UsuarioModel usuarioModel){
//        return usuarioRepository.save(usuarioModel);
//    }

    @PostMapping()
    public ResponseEntity<UsuarioModel> saveUsuario(@RequestBody @Valid UsuarioRecordDto usuarioRecordDto) {
        var usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioRecordDto, usuarioModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuarioModel));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Integer id ) {
        usuarioRepository.findById(id).map(usuarioModel -> {
            usuarioRepository.delete(usuarioModel);
            return usuarioModel;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario não encontrado"));
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @PathVariable Integer id,@RequestBody UsuarioModel usuarioModel){
        usuarioRepository.findById(id).map(existingClient -> {
            usuarioModel.setCodusuario(existingClient.getCodusuario());
            usuarioRepository.save(usuarioModel);
            return existingClient;}).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario não encontrado") );
    }

}
