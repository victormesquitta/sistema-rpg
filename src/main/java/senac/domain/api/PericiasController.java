package senac.domain.api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.domain.dtos.requests.PericiasRequestDTO;
import senac.domain.dtos.responses.PericiasResponseDTO;
import senac.domain.services.PericiasService;

import java.util.List;

@RestController
@RequestMapping("/api/pericias")
public class PericiasController {

    @Autowired
    private PericiasService periciasService;

    @PostMapping
    public ResponseEntity<Object> criarPericia(@RequestBody @Valid PericiasRequestDTO periciasRequestDTO) {
        periciasService.criarPericias(periciasRequestDTO);
        return new ResponseEntity<>("Perícia criada com sucesso.", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PericiasResponseDTO>> listarPericias() {
        List<PericiasResponseDTO> listarPericias = periciasService.listarPericiasResponse();
        return ResponseEntity.ok(listarPericias);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<PericiasResponseDTO> obterPericiaPorId(@PathVariable int id) {
        PericiasResponseDTO periciasDTO = periciasService.obterPericiaPorIdResponse(id);
        return ResponseEntity.ok(periciasDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarPericia(@PathVariable int id, @RequestBody PericiasRequestDTO periciasRequestDTO) {
        periciasService.atualizarPericia(id, periciasRequestDTO);
        return new ResponseEntity<>("Perícia atualizada com sucesso.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirPericia(@PathVariable int id) {
        periciasService.excluirPericia(id);
        return new ResponseEntity<>("Perícia excluída com sucesso.", HttpStatus.OK);
    }
}
