package senac.domain.api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.domain.dtos.requests.MagiaRequestDTO;
import senac.domain.dtos.responses.MagiaResponseDTO;
import senac.domain.services.MagiaService;

import java.util.List;

@RestController
@RequestMapping("/api/magias")
public class MagiaController {

    @Autowired
    private MagiaService magiaService;

    @PostMapping
    public ResponseEntity<Object> criarMagia(@RequestBody @Valid MagiaRequestDTO magiaRequestDTO) {
        magiaService.criarMagia(magiaRequestDTO);
        return new ResponseEntity<>("Magia criada com sucesso.", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MagiaResponseDTO>> listarMagias() {
        List<MagiaResponseDTO> listarMagias = magiaService.listarMagiasResponse();
        return ResponseEntity.ok(listarMagias);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<MagiaResponseDTO> obterMagiaPorId(@PathVariable int id) {
        MagiaResponseDTO magiaDTO = magiaService.obterMagiaPorIdResponse(id);
        return ResponseEntity.ok(magiaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarMagia(@PathVariable int id, @RequestBody MagiaRequestDTO magiaRequestDTO) {
        magiaService.atualizarMagia(id, magiaRequestDTO);
        return new ResponseEntity<>("Magia atualizada com sucesso.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirMagia(@PathVariable int id) {
        magiaService.excluirMagia(id);
        return new ResponseEntity<>("Magia excluída com sucesso.", HttpStatus.OK);
    }
}
