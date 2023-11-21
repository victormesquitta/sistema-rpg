package senac.domain.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.domain.dtos.requests.RegrasRequestDTO;
import senac.domain.dtos.responses.RegrasResponseDTO;
import senac.domain.services.RegrasService;

import java.util.List;

@RestController
@RequestMapping("/api/regras")
public class RegrasController {

    private final RegrasService regrasService;

    @Autowired
    public RegrasController(RegrasService regrasService) {
        this.regrasService = regrasService;
    }

    @PostMapping()
    public ResponseEntity<Object> criarRegras(@RequestBody @Valid RegrasRequestDTO regrasRequestDTO) {
        regrasService.criarRegras(regrasRequestDTO);
        return new ResponseEntity<>("Regras criadas com sucesso.", HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<RegrasResponseDTO>> obterTodasRegras() {
        List<RegrasResponseDTO> todasRegras = regrasService.listarRegrasResponse();
        return ResponseEntity.ok(todasRegras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegrasResponseDTO> obterRegrasPorId(@PathVariable Integer id) {
        RegrasResponseDTO regrasDTO = regrasService.obterRegrasPorIdResponse(id);
        return ResponseEntity.ok(regrasDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizarRegras(@PathVariable Integer id, @RequestBody RegrasRequestDTO regrasRequestDTO) {
        regrasService.atualizarRegras(id, regrasRequestDTO);
        return new ResponseEntity<>("Regras atualizadas com sucesso.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirRegras(@PathVariable Integer id) {
        regrasService.excluirRegras(id);
        return new ResponseEntity<>("Regras excluídas com sucesso.", HttpStatus.OK);
    }
}
