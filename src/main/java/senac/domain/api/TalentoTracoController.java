package senac.domain.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.domain.dtos.requests.TalentoTracoRequestDTO;
import senac.domain.dtos.responses.TalentoTracoResponseDTO;
import senac.domain.services.TalentoTracoService;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/talentostracos")
public class TalentoTracoController {

    @Autowired
    private TalentoTracoService talentoTracoService;

    @PostMapping
    public ResponseEntity<Object> criarTalentoTraco(@RequestBody @Valid TalentoTracoRequestDTO talentoTracoRequestDTO) {
        talentoTracoService.criarTalentoTraco(talentoTracoRequestDTO);
        return new ResponseEntity<>("Talento/Traco criado com sucesso.", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TalentoTracoResponseDTO>> listarTalentosTracos() {
        List<TalentoTracoResponseDTO> listarTalentosTracos = talentoTracoService.listarTalentosTracosResponse();
        return ResponseEntity.ok(listarTalentosTracos);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<TalentoTracoResponseDTO> obterTalentoTracoPorId(@PathVariable int id) {
        TalentoTracoResponseDTO talentoTracoDTO = talentoTracoService.obterTalentoTracoPorIdResponse(id);
        return ResponseEntity.ok(talentoTracoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarTalentoTraco(@PathVariable int id, @RequestBody TalentoTracoRequestDTO talentoTracoRequestDTO) {
        talentoTracoService.atualizarTalentoTraco(id, talentoTracoRequestDTO);
        return new ResponseEntity<>("Talento/Traco atualizado com sucesso.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirTalentoTraco(@PathVariable int id) {
        talentoTracoService.excluirTalentoTraco(id);
        return new ResponseEntity<>("Talento/Traco excluído com sucesso.", HttpStatus.OK);
    }
}
