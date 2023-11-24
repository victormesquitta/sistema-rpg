package senac.domain.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.domain.dtos.requests.AtributosRequestDTO;
import senac.domain.dtos.responses.AtributosResponseDTO;
import senac.domain.services.AtributosService;

import java.util.List;

@RestController
@RequestMapping("/api/atributos")
public class AtributosController {

    @Autowired
    private AtributosService atributosService;

    @PostMapping
    public ResponseEntity<Object> criarAtributos(@RequestBody AtributosRequestDTO atributosRequestDTO) {
        atributosService.criarAtributos(atributosRequestDTO);
        return new ResponseEntity<>("Atributo criado com sucesso.", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AtributosResponseDTO>> listarAtributos() {
        List<AtributosResponseDTO> listarAtributos = atributosService.listarAtributosResponse();
        return ResponseEntity.ok(listarAtributos);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<AtributosResponseDTO> obterAtributosPorId(@PathVariable int id) {
        AtributosResponseDTO atributosDTO = atributosService.obterAtributoPorIdResponse(id);
        return ResponseEntity.ok(atributosDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarAtributos(@PathVariable int id, @RequestBody AtributosRequestDTO atributosRequestDTO) {
        atributosService.atualizarAtributo(id, atributosRequestDTO);
        return new ResponseEntity<>("Atributo atualizado com sucesso.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirAtributos(@PathVariable int id) {
        atributosService.excluirAtributo(id);
        return new ResponseEntity<>("Atributo excluído com sucesso.", HttpStatus.OK);
    }
}
