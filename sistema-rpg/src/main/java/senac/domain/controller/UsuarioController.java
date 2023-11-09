package senac.domain.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import senac.domain.model.Usuario;
import senac.domain.repository.UsuarioRepository;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Usuario> getAllUsers(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario findUserByID(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario save(@RequestBody Usuario usuario){
        return repository.save(usuario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Integer id ) {
        repository.findById(id).map(usuario -> {
            repository.delete(usuario);
            return usuario;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario não encontrado"));
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @PathVariable Integer id,@RequestBody Usuario usuario){
        repository.findById(id).map(existingClient -> {
            usuario.setCodusuario(existingClient.getCodusuario());
            repository.save(usuario);
            return existingClient;}).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario não encontrado") );
    }

}
