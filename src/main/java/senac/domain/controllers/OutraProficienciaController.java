package senac.domain.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.domain.dtos.ambos.OutraProficienciaDTO;
import senac.domain.services.OutraProficienciaService;

import java.util.List;

@RestController
@RequestMapping("/api/outrasproficiencias")
public class OutraProficienciaController {

    @Autowired
    private OutraProficienciaService outraProficienciaService;

    @PostMapping
    public ResponseEntity<Object> criarOutraProficiencia(@RequestBody OutraProficienciaDTO outraProficienciaDTO) {
        outraProficienciaService.criarOutraProficiencia(outraProficienciaDTO);
        return new ResponseEntity<>("Outra proficiência criada com sucesso.", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OutraProficienciaDTO>> listarOutrasProficiencias() {
        List<OutraProficienciaDTO> listarOutrasProficiencias = outraProficienciaService.listarOutrasProficiencias();
        return ResponseEntity.ok(listarOutrasProficiencias);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<OutraProficienciaDTO> obterOutraProficienciaPorId(@PathVariable int id) {
        OutraProficienciaDTO outraProficienciaDTO = outraProficienciaService.obterOutraProficienciaPorId(id);
        return ResponseEntity.ok(outraProficienciaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarOutraProficiencia(@PathVariable int id, @RequestBody OutraProficienciaDTO outraProficienciaDTO) {
        outraProficienciaService.atualizarOutraProficiencia(id, outraProficienciaDTO);
        return new ResponseEntity<>("Outra proficiência atualizada com sucesso.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirOutraProficiencia(@PathVariable int id) {
        outraProficienciaService.excluirOutraProficiencia(id);
        return new ResponseEntity<>("Outra proficiência excluída com sucesso.", HttpStatus.OK);
    }
}
