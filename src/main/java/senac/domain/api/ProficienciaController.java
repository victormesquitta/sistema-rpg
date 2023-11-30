package senac.domain.api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.domain.dtos.requests.ProficienciaRequestDTO;
import senac.domain.dtos.responses.ProficienciaResponseDTO;
import senac.domain.services.ProficienciaService;

import java.util.List;

@RestController
@RequestMapping("/api/proficiencias")
public class ProficienciaController {

    @Autowired
    private ProficienciaService proficienciaService;

    @PostMapping
    public ResponseEntity<Object> criarProficiencia(@RequestBody @Valid ProficienciaRequestDTO proficienciaRequestDTO) {
        proficienciaService.criarProficiencia(proficienciaRequestDTO);
        return new ResponseEntity<>("Proficiência criada com sucesso.", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProficienciaResponseDTO>> listarProficiencias() {
        List<ProficienciaResponseDTO> listarProficiencias = proficienciaService.listarProficienciasResponse();
        return ResponseEntity.ok(listarProficiencias);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ProficienciaResponseDTO> obterProficienciaPorId(@PathVariable int id) {
        ProficienciaResponseDTO proficienciaDTO = proficienciaService.obterProficienciaPorIdResponse(id);
        return ResponseEntity.ok(proficienciaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarProficiencia(@PathVariable int id, @RequestBody ProficienciaRequestDTO proficienciaRequestDTO) {
        proficienciaService.atualizarProficiencia(id, proficienciaRequestDTO);
        return new ResponseEntity<>("Proficiência atualizada com sucesso.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirProficiencia(@PathVariable int id) {
        proficienciaService.excluirProficiencia(id);
        return new ResponseEntity<>("Proficiência excluída com sucesso.", HttpStatus.OK);
    }
}
