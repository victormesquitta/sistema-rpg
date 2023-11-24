package senac.domain.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.domain.dtos.requests.EquipamentoRequestDTO;
import senac.domain.dtos.responses.EquipamentoResponseDTO;
import senac.domain.services.EquipamentoService;

import java.util.List;

@RestController
@RequestMapping("/api/equipamentos")
public class EquipamentoController {

    @Autowired
    private EquipamentoService equipamentoService;

    @PostMapping
    public ResponseEntity<Object> criarEquipamento(@RequestBody @Valid EquipamentoRequestDTO equipamentoRequestDTO) {
        equipamentoService.criarEquipamento(equipamentoRequestDTO);
        return new ResponseEntity<>("Equipamento criado com sucesso.", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EquipamentoResponseDTO>> listarEquipamentos() {
        List<EquipamentoResponseDTO> listarEquipamentos = equipamentoService.listarEquipamentosResponse();
        return ResponseEntity.ok(listarEquipamentos);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<EquipamentoResponseDTO> obterEquipamentoPorId(@PathVariable int id) {
        EquipamentoResponseDTO equipamentoDTO = equipamentoService.obterEquipamentoPorIdResponse(id);
        return ResponseEntity.ok(equipamentoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarEquipamento(@PathVariable int id, @RequestBody EquipamentoRequestDTO equipamentoRequestDTO) {
        equipamentoService.atualizarEquipamento(id, equipamentoRequestDTO);
        return new ResponseEntity<>("Equipamento atualizado com sucesso.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirEquipamento(@PathVariable int id) {
        equipamentoService.excluirEquipamento(id);
        return new ResponseEntity<>("Equipamento excluído com sucesso.", HttpStatus.OK);
    }
}
