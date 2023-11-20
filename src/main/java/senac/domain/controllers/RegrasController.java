package senac.domain.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.domain.dtos.ambos.RegrasDTO;
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
    public ResponseEntity<Object> criarRegras(@RequestBody @Valid RegrasDTO regrasDto) {
        regrasService.criarRegras(regrasDto);
        return new ResponseEntity<>("Regras criadas com sucesso.", HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<RegrasDTO>> obterTodasRegras() {
        List<RegrasDTO> todasRegras = regrasService.listarRegras();
        return ResponseEntity.ok(todasRegras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegrasDTO> obterRegrasPorId(@PathVariable Integer id) {
        RegrasDTO regrasDTO = regrasService.obterRegrasPorId(id);
        return ResponseEntity.ok(regrasDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizarRegras(@PathVariable Integer id, @RequestBody RegrasDTO regrasDto) {
        regrasService.atualizarRegras(id, regrasDto);
        return new ResponseEntity<>("Regras atualizadas com sucesso.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirRegras(@PathVariable Integer id) {
        regrasService.excluirRegras(id);
        return new ResponseEntity<>("Regras excluídas com sucesso.", HttpStatus.OK);
    }
}
