package senac.domain.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.domain.dtos.ambos.PersonagemDTO;
import senac.domain.services.PersonagemService;

import java.util.List;

@RestController
@RequestMapping("/api/personagens")
public class PersonagemController {

    @Autowired
    private PersonagemService personagemService;

    @PostMapping
    public ResponseEntity<Object> criarPersonagem(@RequestBody @Valid PersonagemDTO personagemDTO) {
        personagemService.criarPersonagem(personagemDTO);
        return new ResponseEntity<>("Personagem criado com sucesso.", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PersonagemDTO>> listarPersonagens() {
        List<PersonagemDTO> listarPersonagens = personagemService.listarPersonagens();
        return ResponseEntity.ok(listarPersonagens);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<PersonagemDTO> obterPersonagemPorId(@PathVariable Integer id) {
        PersonagemDTO personagemDTO = personagemService.obterPersonagemPorId(id);
        return ResponseEntity.ok(personagemDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarPersonagem(@PathVariable Integer id, @RequestBody PersonagemDTO PersonagemDTO) {
        personagemService.atualizarPersonagem(id, PersonagemDTO);
        return new ResponseEntity<>("Personagem atualizado com sucesso.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirPersonagem(@PathVariable Integer id) {
        personagemService.excluirPersonagem(id);
        return new ResponseEntity<>("Personagem excluído com sucesso.", HttpStatus.OK);
    }

}
