package senac.domain.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.domain.dtos.requests.OutraProficienciaRequestDTO;
import senac.domain.dtos.responses.OutraProficienciaResponseDTO;
import senac.domain.services.OutraProficienciaService;

import java.util.List;

@RestController
@RequestMapping("/api/outrasproficiencias")
public class OutraProficienciaController {

    @Autowired
    private OutraProficienciaService outraProficienciaService;

    @PostMapping
    public ResponseEntity<Object> criarOutraProficiencia(@RequestBody OutraProficienciaRequestDTO outraProficienciaRequestDTO) {
        outraProficienciaService.criarOutraProficiencia(outraProficienciaRequestDTO);
        return new ResponseEntity<>("Outra proficiência criada com sucesso.", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OutraProficienciaResponseDTO>> listarOutrasProficiencias() {
        List<OutraProficienciaResponseDTO> listarOutrasProficiencias = outraProficienciaService.listarOutrasProficienciasResponse();
        return ResponseEntity.ok(listarOutrasProficiencias);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<OutraProficienciaResponseDTO> obterOutraProficienciaPorId(@PathVariable int id) {
        OutraProficienciaResponseDTO outraProficienciaResponseDTO = outraProficienciaService.obterOutraProficienciaPorIdResponse(id);
        return ResponseEntity.ok(outraProficienciaResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarOutraProficiencia(@PathVariable int id, @RequestBody OutraProficienciaRequestDTO outraProficienciaRequestDTO) {
        outraProficienciaService.atualizarOutraProficiencia(id, outraProficienciaRequestDTO);
        return new ResponseEntity<>("Outra proficiência atualizada com sucesso.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirOutraProficiencia(@PathVariable int id) {
        outraProficienciaService.excluirOutraProficiencia(id);
        return new ResponseEntity<>("Outra proficiência excluída com sucesso.", HttpStatus.OK);
    }
}
