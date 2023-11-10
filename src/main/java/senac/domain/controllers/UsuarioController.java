package senac.domain.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import senac.domain.models.UsuarioModel;
import senac.domain.repositories.UsuarioRepository;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/usuarios")
public class UsuarioController {

    //@Autowired
    private UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<UsuarioModel> getAllUsers(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public UsuarioModel findUserByID(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioModel save(@RequestBody UsuarioModel usuarioModel){
        return repository.save(usuarioModel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Integer id ) {
        repository.findById(id).map(usuarioModel -> {
            repository.delete(usuarioModel);
            return usuarioModel;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario não encontrado"));
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @PathVariable Integer id,@RequestBody UsuarioModel usuarioModel){
        repository.findById(id).map(existingClient -> {
            usuarioModel.setCodusuario(existingClient.getCodusuario());
            repository.save(usuarioModel);
            return existingClient;}).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario não encontrado") );
    }

}
