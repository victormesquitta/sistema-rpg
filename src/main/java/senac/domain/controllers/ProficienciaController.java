package senac.domain.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.domain.dtos.ambos.ProficienciaDTO;
import senac.domain.services.ProficienciaService;

import java.util.List;

@RestController
@RequestMapping("/api/proficiencias")
public class ProficienciaController {

    @Autowired
    private ProficienciaService proficienciaService;

    @PostMapping
    public ResponseEntity<Object> criarProficiencia(@RequestBody @Valid ProficienciaDTO proficienciaDTO) {
        proficienciaService.criarProficiencia(proficienciaDTO);
        return new ResponseEntity<>("Proficiência criada com sucesso.", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProficienciaDTO>> listarProficiencias() {
        List<ProficienciaDTO> listarProficiencias = proficienciaService.listarProficiencias();
        return ResponseEntity.ok(listarProficiencias);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ProficienciaDTO> obterProficienciaPorId(@PathVariable int id) {
        ProficienciaDTO proficienciaDTO = proficienciaService.obterProficienciaPorId(id);
        return ResponseEntity.ok(proficienciaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarProficiencia(@PathVariable int id, @RequestBody ProficienciaDTO proficienciaDTO) {
        proficienciaService.atualizarProficiencia(id, proficienciaDTO);
        return new ResponseEntity<>("Proficiência atualizada com sucesso.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirProficiencia(@PathVariable int id) {
        proficienciaService.excluirProficiencia(id);
        return new ResponseEntity<>("Proficiência excluída com sucesso.", HttpStatus.OK);
    }
}
