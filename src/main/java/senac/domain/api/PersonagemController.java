package senac.domain.api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.domain.dtos.requests.PersonagemRequestDTO;
import senac.domain.dtos.responses.PersonagemResponseDTO;
import senac.domain.services.PericiasService;
import senac.domain.services.PersonagemService;

import java.util.List;

@RestController
@RequestMapping("/api/personagens")
public class PersonagemController {


    private final PersonagemService personagemService;
    private final PericiasService periciasService;

    @Autowired
    public PersonagemController(PersonagemService personagemService, PericiasService periciasService) {
        this.personagemService = personagemService;
        this.periciasService = periciasService;
    }


    @PostMapping
    public ResponseEntity<Object> criarPersonagem(@RequestBody @Valid PersonagemRequestDTO personagemRequestDTO) {
        periciasService.criarPericiasComPersonagem(personagemService.criarPersonagem(personagemRequestDTO));
        return new ResponseEntity<>("Personagem criado com sucesso.", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PersonagemResponseDTO>> listarPersonagens() {
        List<PersonagemResponseDTO> listarPersonagens = personagemService.listarPersonagensResponse();
        return ResponseEntity.ok(listarPersonagens);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<PersonagemResponseDTO> obterPersonagemPorId(@PathVariable Integer id) {
        PersonagemResponseDTO personagemDTOResponse = personagemService.obterPersonagemPorIdResponse(id);
        return ResponseEntity.ok(personagemDTOResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarPersonagem(@PathVariable Integer id, @RequestBody PersonagemRequestDTO personagemRequestDTO) {
        personagemService.atualizarPersonagem(id, personagemRequestDTO);
        return new ResponseEntity<>("Personagem atualizado com sucesso.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirPersonagem(@PathVariable Integer id) {
        personagemService.excluirPersonagem(id);
        return new ResponseEntity<>("Personagem excluído com sucesso.", HttpStatus.OK);
    }

}
